package com.springBajo8.springBajo8.controller;

public class TratamientoYPadecimiento {
    private  String tratamiento;
    private  String padecimiento;

    public TratamientoYPadecimiento(String tratamiento, String padecimiento) {
        this.tratamiento = tratamiento;
        this.padecimiento = padecimiento;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getPadecimiento() {
        return padecimiento;
    }

    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }

    @Override
    public String toString() {
        return "TratamientoYPadecimiento{" +
                "tratamiento='" + tratamiento + '\'' +
                ", padecimiento='" + padecimiento + '\'' +
                '}';
    }
}
