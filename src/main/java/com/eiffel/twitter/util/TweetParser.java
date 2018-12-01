package com.eiffel.twitter.util;

import com.eiffel.twitter.model.Hashtag;
import com.eiffel.twitter.model.User;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetParser {

    public Set<String> mentions = new HashSet<>();

    private static final int  tweetLength = 140;

    public Set<Hashtag> hashtags = new HashSet<>();;

    public TweetParser(String tweet) {
        findMentions(tweet);
        findHashtags(tweet);
    }

    public void findMentions(String tweet){
        String pattern = "\\@([^\\s]+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(tweet);
        while (m.find()){
            this.mentions.add(m.group().toLowerCase());
        }
    }

    public void findHashtags(String tweet){
        String pattern = "\\#([^\\s]+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(tweet);
        while (m.find()){
            Hashtag h = new Hashtag(m.group().toLowerCase());
            this.hashtags.add(h);
        }
    }
}
