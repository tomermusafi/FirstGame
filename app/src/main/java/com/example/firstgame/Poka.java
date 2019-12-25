package com.example.firstgame;

public class Poka {

   int img;
   int tag;

   public Poka(){

   }

   public Poka(int img, int tag){
       this.img = img;
       this.tag = tag;
   }

    public int getImg() {
        return img;
    }

    public Poka setImg(int img) {
        this.img = img;
        return this;
    }

    public int getTag() {
        return tag;
    }
}
