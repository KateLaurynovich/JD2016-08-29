package by.it.savelyeva.matlab.vars;

import by.it.savelyeva.matlab.VarDimensionException;
import by.it.savelyeva.matlab.interfaces.IVariable;

/**
 * Created by nato on 9/16/16.
 */
public class VarFloat extends Var implements IVariable {

    private double value;

    public VarFloat(double value) {
        this.value = value;
    }

    public VarFloat(String s) {
        this.value = ((VarFloat) fromString(s)).value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public Var add(Var var) throws VarDimensionException {
        if (var instanceof VarFloat) {
            return new VarFloat(this.value + ((VarFloat) var).value);
        }
        return var.add(this);
    }

    @Override
    public Var sub(Var var) throws VarDimensionException {
        if (var instanceof VarFloat) {
            return new VarFloat(this.value - ((VarFloat) var).value);
        }
        VarFloat minus = new VarFloat(-1);
        return (minus.mul(var)).add(this);
    }

    @Override
    public Var mul(Var var) throws VarDimensionException {
        if (var instanceof VarFloat) {
            return new VarFloat(this.value * ((VarFloat) var).value);
        }
        return var.mul(this);
    }

    @Override
    public Var div(Var var) throws ArithmeticException {
        if (var instanceof VarFloat) {
            if ((int)((VarFloat) var).getValue() == 0) throw new ArithmeticException("Caught error: division by zero!");
            else return new VarFloat(this.value / ((VarFloat) var).value);
        }
        return super.div(this);
    }

    @Override
    public String toString() {
        return ((Double) value).toString();
    }

    public String toString(String format) {
        return String.format(format, (Double) this.value);
    }

    public static Var fromString(String s) {
        return (Var) new VarFloat(Double.parseDouble(s));
    }

}

