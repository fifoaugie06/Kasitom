package com.example.kasitom.model;

public class dataScoreBoard {
    String nama, photoURI, correct, nilai;

    public dataScoreBoard(){

    }

    public dataScoreBoard(String nama, String photoURI, String correct, String nilai) {
        this.nama = nama;
        this.photoURI = photoURI;
        this.correct = correct;
        this.nilai = nilai;
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
}
