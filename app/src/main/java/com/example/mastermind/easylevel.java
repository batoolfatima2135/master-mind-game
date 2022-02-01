package com.example.mastermind;

public class easylevel {
private String id,hint,word;

    public easylevel(String id, String hint, String word) {
        this.id = id;
        this.hint = hint;
        this.word = word;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
