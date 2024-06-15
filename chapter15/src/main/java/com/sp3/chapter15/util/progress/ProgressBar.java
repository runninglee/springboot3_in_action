package com.sp3.chapter15.util.progress;


public class ProgressBar {

    private final int totalSteps;
    private int currentStep;

    public ProgressBar(int totalSteps) {
        this.totalSteps = totalSteps;
        this.currentStep = 0;
    }

    public void updateProgress(int progress) {
        currentStep = progress;
        draw();
    }

    private void draw() {
        double percentage = (double) currentStep / totalSteps * 100;
        StringBuilder progressBar = new StringBuilder("[");
        int complete = (int) (percentage / 2);
        for (int i = 0; i < 50; i++) {
            if (i <= complete) {
                progressBar.append("#");
            } else {
                progressBar.append(" ");
            }
        }
        progressBar.append(String.format("] %.2f%%", percentage));
        System.out.print("\r" + progressBar.toString());
        if (currentStep == totalSteps) {
            System.out.println();
        }
    }
}