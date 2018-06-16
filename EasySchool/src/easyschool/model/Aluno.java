/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyschool.model;

import java.awt.List;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author aluno
 */
public class Aluno extends Usuario {

    private String curso;
    private String periodo;
    private ArrayList materias;

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
        setaMaterias();
    }

    public ArrayList getMaterias() {
        return materias;
    }

    private void setaMaterias() {
        ArrayList listMaterias = new ArrayList();
        String p1[] = {Constants.CALCULO_1, Constants.ALGEBRA, Constants.CIRCUITOS};
        String p2[] = {Constants.CALCULO_2, Constants.FISICA_1, Constants.ALGORITMO_1};
        String p3[] = {Constants.CALCULO_3, Constants.FISICA_2, Constants.ALGORITMO_2};
        String p4[] = {Constants.CALCULO_NUMERICO, Constants.ORIENTACAO_OBJETO, Constants.FISICA_3};
        String p5[] = {Constants.METODOLOGIA, Constants.ORIENTACAO_OBJETO, Constants.FISICA_3};
        String p6[] = {Constants.METODOLOGIA, Constants.ORIENTACAO_OBJETO, Constants.FISICA_3};
        String p7[] = {Constants.METODOLOGIA, Constants.ORIENTACAO_OBJETO, Constants.FISICA_3};
        String p8[] = {Constants.METODOLOGIA, Constants.ORIENTACAO_OBJETO, Constants.FISICA_3};
        String p9[] = {Constants.METODOLOGIA, Constants.ORIENTACAO_OBJETO, Constants.FISICA_3};
        String p10[] = {Constants.METODOLOGIA, Constants.ORIENTACAO_OBJETO, Constants.FISICA_3};

        switch (this.periodo) {
            case "1º Período":
                for (int i = 0; i < p1.length; i++) {
                    listMaterias.add(p1[i]);
                }
                break;
            case "2º Período":
                for (int i = 0; i < p2.length; i++) {
                    listMaterias.add(p2[i]);
                }
                break;
            case "3º Período":
                for (int i = 0; i < p3.length; i++) {
                    listMaterias.add(p3[i]);
                }
                break;
            case "4º Período":
                for (int i = 0; i < p4.length; i++) {
                    listMaterias.add(p4[i]);
                }
                break;
            case "5º Período":
                for (int i = 0; i < p5.length; i++) {
                    listMaterias.add(p5[i]);
                }
                break;
            case "6º Período":
                for (int i = 0; i < p6.length; i++) {
                    listMaterias.add(p6[i]);
                }
                break;
            case "7º Período":
                for (int i = 0; i < p7.length; i++) {
                    listMaterias.add(p7[i]);
                }
                break;
            case "8º Período":
                for (int i = 0; i < p8.length; i++) {
                    listMaterias.add(p8[i]);
                }
                break;
            case "9º Período":
                for (int i = 0; i < p9.length; i++) {
                    listMaterias.add(p9[i]);
                }
                break;
            case "10º Período":
                for (int i = 0; i < p10.length; i++) {
                    listMaterias.add(p10[i]);
                }
                break;
        }

        materias = listMaterias;
    }

}
