package com.example.keeptasks;

import java.io.IOException;

public class callpy {
  public static void main(String[] args) throws IOException, InterruptedException {
    ProcessBuilder process = new ProcessBuilder("python3", System.getProperty("user.dir")+"/<script name>.py").inheritIO();
    Process demo = process.start();
  }
}