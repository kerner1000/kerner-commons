package de.kerner.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CommandStringBuilder {
	
	private final List<String> sb = new ArrayList<String>();
	
	public CommandStringBuilder(String executable){
		sb.add(executable);
	}
	
	public void addValueCommand(String identifier, String value){
		sb.add(identifier);
		sb.add(value);
	}
	
	public void addAllValueCommands(Map<String, String> all){
		for(Entry<String, String> e : all.entrySet()){
			sb.add(e.getKey());
			sb.add(e.getValue());
		}
	}
	
	public void addAllFlagCommands(String ...all){
		sb.addAll(Arrays.asList(all));
	}
	
	public void addAllFlagCommands(List<String> all){
		sb.addAll(all);
	}
	
	public void addFlagCommand(String flagCommand){
		sb.add(flagCommand);
	}
	
	public List<String> getCommandList(){
		return sb;
	}
	
	public String toString(){
		return sb.toString();
	}
}
