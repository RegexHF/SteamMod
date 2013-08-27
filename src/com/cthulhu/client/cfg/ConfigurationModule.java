package com.cthulhu.client.cfg;

import java.util.HashMap;

public class ConfigurationModule
{

	String name;
	String label;
	String description;
	String usage;
	String version;
	String author;
	String dateCreated;
	String dateModified;
	String keyWord;
	HashMap<String, String> hashMap = new HashMap<String, String>();

	public ConfigurationModule(String name, String label, String description, String usage, String version, String author, String dateCreated, String dateModified, String keyWord, HashMap<String, String> hashMap)
	{
		setName(name);
		setLabel(label);
		setDescription(description);
		setUsage(usage);
		setVersion(version);
		setAuthor(author);
		setDateCreated(dateCreated);
		setDateModified(dateModified);
		setKeyWord(keyWord);
		setHashMap(hashMap);
	}
	
	public HashMap<String, String> getHashMap() {
		return hashMap;
	}

	public void setHashMap(HashMap<String, String> hashMap) {
		this.hashMap = hashMap;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDateModified() {
		return dateModified;
	}
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	public void readLine(String line, String split)
	{
		try
		{
			if(line.startsWith(keyWord))
			{
				String args[] = line.split(split);
				if(hashMap != null)
				{
					hashMap.put(args[0], args[1]);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
