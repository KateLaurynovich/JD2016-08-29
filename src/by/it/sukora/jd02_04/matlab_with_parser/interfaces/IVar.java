package by.it.sukora.jd02_04.matlab_with_parser.interfaces;

public interface IVar extends IAdd,IDiv,IMul,IMul.ISub {
    String toString();            //метод вывода переменной в строку
    void setFrom(String str);     //метод чтения значения переменной из строки
}

