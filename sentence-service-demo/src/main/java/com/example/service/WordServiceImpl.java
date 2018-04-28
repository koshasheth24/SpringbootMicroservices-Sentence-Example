package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AdjectiveClient;
import com.example.dao.ArticleClient;
import com.example.dao.NounClient;
import com.example.dao.SubjectClient;
import com.example.dao.VerbClient;
import com.example.domain.Word;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class WordServiceImpl implements WordService {

	@Autowired VerbClient verbClient;
	@Autowired SubjectClient subjectClient;
	@Autowired ArticleClient articleClient;
	@Autowired AdjectiveClient adjectiveClient;
	@Autowired NounClient nounClient;
	
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackSubject")
	public Word getSubject() {
		return subjectClient.getWord();
	}
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackVerb")
	public Word getVerb() {
		return verbClient.getWord();
	}
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackArticle")
	public Word getArticle() {
		return articleClient.getWord();
	}
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackAdjective")
	public Word getAdjective() {
		return adjectiveClient.getWord();
	}
	
	@Override
	@HystrixCommand(fallbackMethod="getFallbackNoun")
	public Word getNoun() {
		return nounClient.getWord();
	}	

	public Word getFallbackSubject() {
		return new Word("Missing");
	}
	
	public Word getFallbackAdjective() {
		return new Word("Missing");
	}
	
	public Word getFallbackNoun() {
		return new Word("Missing");
	}
	
	public Word getFallbackVerb() {
		return new Word("Missing");
	}
	
	public Word getFallbackArticle() {
		return new Word("Missing");
	}

}
