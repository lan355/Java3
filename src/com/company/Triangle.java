package com.company;
import lombok.Getter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Triangle implements ITriangle
{
    @Getter
    protected final double side1;
    protected final double side2;
    protected final double side3;

    public Triangle(double side1, double side2, double side3)
    {
            this.side1 = setSide(side1);
            this.side2 = setSide(side2);
            this.side3 = setSide(side3);

    }

    public boolean ExistsTriangles()
    {
        return side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1;
    }


    public double setSide(double side)
    {
        return side > 0 ? side : 1;
    }

    public double getPerimeter()
    {
        return side1 + side2 + side3;
    }

    public double getAngle1()
    {
        return (double) Math.round( Math.toDegrees( Math.acos(( Math.pow(side2, 2) + Math.pow(side3, 2) - Math.pow(side1, 2) ) / (2 * side2 * side3))));
    }
    public double getAngle2()
    {
        return (double) Math.round(Math.toDegrees( Math.acos(( Math.pow(side1, 2) + Math.pow(side3, 2) - Math.pow(side2, 2) ) / (2 * side1 * side3))));
    }
    public double getAngle3()
    {
        return (double) Math.round( Math.toDegrees( Math.acos(( Math.pow(side1, 2) + Math.pow(side2, 2) - Math.pow(side3, 2) ) / (2 * side1 * side2))));
    }
    public String Save() throws IOException
    {
        String save = getInfoOfTriangle();
        String fileSave = "save.txt";
        BufferedWriter file = new BufferedWriter(new FileWriter(fileSave, true));
        file.write(save + "\n");
        System.out.println("Треугольники:\n");
        file.newLine();
        file.close();
        return save;
    }


    @Override
    public double getSquare()
    {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }

    @Override
    public String getInfoOfTriangle()
    {   if (!this.ExistsTriangles())
        {
            return "\nТакой треугольник не существует \nSide1:" + side1 + "\nSide2:" + side2 + "\nSide3:" + side3;
        }
        return "Side 1:" + side1 + "\nSide 2:" + side2 + "\nSide 3:" + side3 + "\nAngle1:" + getAngle1() + "\nAngle2:" + getAngle2() + "\nAngle3:" + getAngle3() + "\nPerimeter:" + getPerimeter() + "\nSquare:" + getSquare();
    }

}