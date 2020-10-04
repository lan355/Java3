package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {

    final static List<Triangle> listN = new ArrayList<>();
    final static List<TriangleIsosceles> listM = new ArrayList<>();
    final static Random random = new Random();

    public static void main(String[] args) throws IOException
    {
        File newFile = new File("save.txt");
        try
        {
            boolean created = newFile.createNewFile();
            if(created)
                System.out.println("Файл создан");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        System.out.println(Load());

        System.out.println("Введите N треугольников");
        int N = readInt();
        System.out.println("Введите M равнобедренных треугольников");
        int M = readInt();

        System.out.println("Выводим треугольники:");
        float averSquare = 0;
        for(int i = 0; i < N; i++)
        {
            Triangle triangle = getRandTriangle();
            System.out.println(i + 1 + ".\n" + triangle.getInfoOfTriangle() + "\n");
            averSquare += triangle.getSquare();
            listN.add(triangle);
            System.out.println("\n");
            System.out.println(triangle.Save());
        }
        averSquare /= N;
        System.out.print("Средняя площадь треугольников: " + averSquare);
        System.out.println("\n");

        System.out.println("Выводим равнобедренные треугольники:");
        double maxSquare = 0;
        //TriangleIsosceles minSquareTriangleIsosceles = null;
        for(int i = 0; i < M; i++)
        {
            TriangleIsosceles isosceles = getRandTriangleIsosceles();
            System.out.println(i + 1 + ".\n" + isosceles.getInfoOfTriangle() + "\n");
            System.out.println(isosceles.SaveTriangleIsosceles());
            if(isosceles.getSquare() > maxSquare)
            {
                maxSquare = isosceles.getSquare();
            }
            listM.add(isosceles);
        }
        System.out.println("Треугольники равнобедренные с наибольшей площадью:" + maxSquare);

        Path source = Paths.get("save.txt");
        Path target = Paths.get("save2.txt");
        try {
            Files.copy(source, target);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static Triangle getRandTriangle()
    {

        return new Triangle((double) random.nextInt(15) + 1,(double) random.nextInt(15) + 1,(double) random.nextInt(15) + 1);
    }

    public static TriangleIsosceles getRandTriangleIsosceles()
    {
        return new TriangleIsosceles((double) random.nextInt(5) + 1,(double) random.nextInt(5) + 1,(double) random.nextInt(5) + 1,(double) random.nextInt(5) + 1);
    }

    public static int readInt()
    {
        Scanner input = new Scanner(System.in);
        while(true)
        {
            try
            {
                int result = Integer.parseInt(input.next());
                if(result > 0) return result;
                else
                {
                    System.out.println("Число треугольников должно > 0");
                }
            }
            catch (Exception e)
            {
                System.out.println("Введите еще раз");
            }
        }
    }

    public static String Load() throws IOException
    {
        StringBuilder loadString = new StringBuilder();
        String fileName = "save.txt";
       BufferedReader load = new BufferedReader(new FileReader(fileName));

       while ((fileName = load.readLine()) != null)
       {
           loadString.append('\n').append(fileName);
       }
       load.close();
       return loadString.toString();
    }

}
