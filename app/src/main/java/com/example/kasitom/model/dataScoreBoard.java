package com.example.kasitom.model;

public class dataScoreBoard {
    String nama, photoURI, correct, nilai, size, key;

    public dataScoreBoard(){

    }

    public dataScoreBoard(String nama, String photoURI, String correct, String nilai, String size) {
        this.nama = nama;
        this.photoURI = photoURI;
        this.correct = correct;
        this.nilai = nilai;
        this.size = size;
    }

    public String getNama() {
        return nama;
    }

    public String getPhotoURI() {
        return photoURI;
    }

    public String getCorrect() {
        return correct;
    }

    public String getNilai() {
        return nilai;
    }

    public String getSize() {
        return size;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
