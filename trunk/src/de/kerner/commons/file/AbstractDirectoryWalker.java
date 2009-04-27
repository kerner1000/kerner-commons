package de.kerner.commons.file;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.List;

/**
 * <p>
 * <code>AbstractDirectoryWalker</code> - walk through a directory hierarchy
 * recursively
 * </p>
 * <code>AbstractDirectoryWalker</code> will start at a given starting depth
 * relatively to the given directory and walk recursively through the directory
 * hierarchy until stopping depth is reached. <br>
 * On its way, it will call "handleFile()" and "handleDir()" for every file and
 * directory, that is not filtered out by any of the filteres set for this
 * DirectoryWalker.
 * 
 * @author Alexander Kerner
 * 
 */
public abstract class AbstractDirectoryWalker {

    private class FileObject {
        final File file;
        final Long depth;

        FileObject(File file, Long depth) {
            this.file = file;
            this.depth = depth;
        }
    }

    private final Stack<FileObject> stack = new Stack<FileObject>();
    private final Set<FileFilter> filters = new HashSet<FileFilter>();
    private volatile boolean cancelled = false;
    private volatile Long maxDepth = null;
    private volatile Long minDepth = null;
    //protected volatile File currentFile = null;

    /**
     * Main method to walk through directory hierarchy.
     * 
     * @param dirs Array of <code>File</code> to walk through.
     * @throws IOException if any IO error occurs.
     */
    public synchronized void walk(final File... dirs) throws IOException {
        if (dirs == null || dirs.length == 0) {
            throw new NullPointerException("Directory to walk from must not be null");
        }
        for (File dir : dirs) {
            if (dir == null || !dir.isDirectory())
                throw new IOException("No such directory " + dir);
            
            /* See note in handleChilds()
            dir = dir.getCanonicalPath();
            */
            handleStartingDir(dir);
            stack.push(new FileObject(dir, 0L));
            while (!cancelled && !stack.isEmpty()) {
                handle(stack.pop());
            }
            lastAction(cancelled);
        }
    }
    
    public synchronized void walk(final List<File> dirs) throws IOException {
        walk(dirs.toArray(new File[0]));
    }
    
    private void handle(final FileObject file) throws IOException {
        if ((minDepth != null && file.depth < minDepth) ||
                (maxDepth != null && file.depth > maxDepth)) {
            // out of boundaries
        } else if (notFiltered(file)) {
            handleFileOrDir(file);
        } else {
            // filtered out
        }
        try {
            handleChilds(file);
        } catch (SecurityException e) {
            // Exception rises, when access to folder content was denied
            handleRestrictedFile(file.file);
        }
    }

    private void handleChilds(final FileObject file) throws IOException, SecurityException {
        final Stack<File> stack = new Stack<File>();
        final File[] content = file.file.listFiles();
        if (content == null) {
            // I/O Error or file
        } else if (content.length == 0) {
            // dir is empty
        } else {
            for (File f : content) {
                if(isNoSymLink(f))
                stack.push(f);
            }
            while (!stack.isEmpty()) {
                this.stack.push(new FileObject(stack.pop(), file.depth + 1));
            }
        }

    }

    private void handleFileOrDir(final FileObject file) throws IOException {
        //currentFile = file.file;
        if (file.file.isDirectory())
            handleDir(file.file);
        else if (file.file.isFile())
            handleFile(file.file);
        else {
            // ignore unknown file type
        }
    }

    private boolean notFiltered(final FileObject file) {
        if (!filters.isEmpty())
            for (FileFilter filter : filters)
                if (!filter.accept(file.file))
                    return false;
        return true;
    }

    /**
     * Abort walking.
     */
    public void stopWalking() {
        cancelled = true;
    }

    /**
     * 
     * @param min starting depth at which actual action performing is started.
     */
    public void setMinDepth(Long min) {
        minDepth = min;
    }

    /**
     * 
     * @param max ending depth at which actual action performing is stopped.
     */
    public void setMaxDepth(Long max) {
        maxDepth = max;
    }

    /**
     * 
     * @param filter <code>FileFilter</code> to be added to this
     *            DirectoryWalkers FilterSet.
     */
    public synchronized void addFilter(FileFilter filter) {
        filters.add(filter);
    }
    
    private boolean isNoSymLink(File file) throws IOException{
        return file.getCanonicalPath().equals(file.getAbsolutePath());
    }

    /**
     * This method is called, when access to a file was denied.<br>
     * Default implementation will rise a <code>IOException</code> instead of
     * <code>SecurityException</code>. Maybe overridden by extending classes to
     * do something else.
     * 
     * @param file <code>File</code>-object, to which access was restricted.
     * @throws IOException in default implementation.
     */
    protected void handleRestrictedFile(final File file) throws IOException {
        throw new IOException("Permission denied for " + file);
    }

    /**
     * This method is called, when walking is about to start.<br>
     * By default, it does nothing. Maybe overridden by extending classes to do
     * something else.
     * 
     * @param file <code>File</code>-object, that represents starting dir.
     * @throws IOException if IO error occurs.
     */
    protected void handleStartingDir(final File file) throws IOException {
        // do nothing by default
    }
    
    /**
     * This method is called, when walking has finished. <br>
     * By default, it does nothing. Maybe overriden by extending classes to do something else.
     * @param wasCancelled true, if directory walking was aborted.
     */
    protected void lastAction(boolean wasCancelled){
        // do nothing by default
    }

    /**
     * 
     * @param file <code>File</code>-object, that represents current directory. <br>
     *            Override this, to do some actions on this directory.
     * @throws IOException if IO error occurs.
     */
    public abstract void handleDir(final File file) throws IOException;

    /**
     * 
     * @param file <code>File</code>-object, that represents current file. <br>
     *            Override this, to do some actions on this file.
     * @throws IOException if IO error occurs.
     */
    public abstract void handleFile(final File file) throws IOException;

}