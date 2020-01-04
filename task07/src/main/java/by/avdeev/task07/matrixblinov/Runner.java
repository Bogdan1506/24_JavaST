package by.avdeev.task07.matrixblinov;

import by.avdeev.task07.matrixblinov.service.impl.MultiplicatorServiceImpl;
import by.avdeev.task07.matrixblinov.creator.MatrixCreator;
import by.avdeev.task07.matrixblinov.entity.Matrix;
import by.avdeev.task07.matrixblinov.exceptions.MatrixException;

public class Runner {
    public static void main(String[] args) {
        try {
            Matrix p = new Matrix(2, 3);
            MatrixCreator.fillRandomized(p, 2, 8);
            System.out.println("Matrix first is: " + p);
            Matrix q = new Matrix(3, 4);
            MatrixCreator.fillRandomized(q, 2, 7);
            System.out.println("Matrix second is: " + q);
            MultiplicatorServiceImpl mult = new MultiplicatorServiceImpl();
            System.out.println("Matrices product is " + mult.multiply(p, q));
        } catch (MatrixException ex) {
            System.err.println("Error of creating matrix " + ex);
        }
    }
}
