package com.packages.joe.mathscratchandroid.equation;



import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;

public class Equation {
    String equation;
    boolean complex;//if a mix of operators will be used
    int operands;//how many operands
    public enum Difficulty {
        EASY(0), MEDIUM(1), HARD(2), VERY_HARD(3);
        int n;
        Difficulty(int n){
            this.n = n;
        }

        public int getN() {
            return n;
        }
    }
    boolean correct;
    boolean wrong;

    public boolean isCorrect() {
        return correct;
    }

    public boolean isWrong() {
        return wrong;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setWrong(boolean wrong) {
        this.wrong = wrong;
    }
    String guessedAnswer = "";

    public void setGuessedAnswer(String guessedAnswer) {
        this.guessedAnswer = guessedAnswer;
    }

    public String getGuessedAnswer() {
        return guessedAnswer;
    }

    public String getEquation() {
        return equation;
    }
    BigDecimal answer;
    Difficulty difficulty;
    Random random;
    public Equation(Difficulty difficulty, int operands, boolean complex){
        correct = false;
        wrong = false;
        this.difficulty = difficulty;
        random = new SecureRandom();
        this.complex = complex;
        this.operands = operands;
        int operatorType = 0;
        equation = "";
        if(!complex){
            operatorType = random.nextInt(2);
        }
        if (this.difficulty == Difficulty.EASY){
            int operand = 1;
            for(int i = 0; i < operands; i++){
                System.out.println(operand);
                if(operatorType == 1 && i > 0){
                    operand = random.nextInt(operand+1);
                }
                else {
                    operand = random.nextInt(99);
                }
                equation = equation + operand;
                System.out.println("Equation: " + equation);
                if(i == operands -1) break;
                else {
                    if(complex){
                        operatorType = random.nextInt(2);
                        System.out.println("Operator Type: " + operatorType);
                        if(operatorType == 0) equation += "+";
                        else if(operatorType == 1) equation += "-";

                    } else {
                        if(operatorType == 0) equation += "+";
                        else if(operatorType == 1) equation += "-";

                    }
                }
            }
            System.out.println("Equation: " + equation);
        } else if(this.difficulty == Difficulty.HARD){
            int operand = 1;
            for(int i = 0; i < operands; i++){
                System.out.println(operand);
                if(operatorType == 1 && i > 0){
                    operand = random.nextInt(operand+1);
                }
                else {
                    operand = random.nextInt(999);
                }
                equation = equation + operand;
                System.out.println("Equation: " + equation);
                if(i == operands -1) break;
                else {
                    if(complex){
                        operatorType = random.nextInt(2);
                        System.out.println("Operator Type: " + operatorType);
                        if(operatorType == 0) equation += "+";
                        else if(operatorType == 1) equation += "-";

                    } else {
                        if(operatorType == 0) equation += "+";
                        else if(operatorType == 1) equation += "-";

                    }
                }
            }
        }
        Expression expression = new Expression(equation);
        answer = expression.eval();
    }
    public Equation(boolean multiplication, int a, int b){
        if(multiplication){
            equation = "" + a + "* " + b;
            Expression expression = new Expression(equation);
            answer = expression.eval();
        }

    }
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public BigDecimal getAnswer() {
        return answer;
    }
    public Equation(String equation, String guessedAnswer){
        this.equation = equation;
        this.guessedAnswer = guessedAnswer;
        answer = new Expression(equation).eval();
        try {
            int guess = Integer.parseInt(guessedAnswer);
            if (BigDecimal.valueOf(guess).equals(answer)) {
                setCorrect(true);
            } else {
                setWrong(true);
            }
        } catch (NumberFormatException e){

        }
    }
}
