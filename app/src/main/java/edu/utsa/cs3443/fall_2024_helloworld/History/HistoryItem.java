package edu.utsa.cs3443.fall_2024_helloworld.History;

public class HistoryItem {
    public String calculatorName;
    public double output;
    public String[] inputs;
    public HistoryItem(String calculatorName, double output, String... inputs){
        this.calculatorName = calculatorName;
        this.output = output;
        this.inputs = inputs;
    }
}
