package by.it.annazhegulovich.jd02_04;


class Matrix extends Var {

    private final double[][] value2;

    public Matrix(double[][] value) {
        value2 = new double[value.length][value.length];
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value.length; j++) {
                value2 [i][j] = value [i][j];
            }
        }
    }
    public Matrix(Matrix matrix){
        this.value2= matrix.value2;
    }
    public Matrix(String strMatrix){

        String [] res= strMatrix.split("},");
        this.value2 = new double[res.length][];
        for (int i = 0; i <res.length ; i++) {
            String strRes= res[i];
            strRes = strRes.replace("{","")
                    .replace("}","")
                    .replace(" ","");
            String [] resi= strRes.split(",");
            value2[i]=new  double[resi.length];
            for (int j = 0; j < value2[i].length; j++) {
                this.value2[i][j]= Double.parseDouble(resi[j]);
            }
        }
    }

    @Override
    public Var add(Var other) {
        double[][] res = new double[value2.length][value2[0].length];
        for (int i = 0; i < value2.length; i++) {
            for (int j = 0; j < value2[i].length; j++) {
                res[i][j] = value2[i][j];
            }
        }
        if (other instanceof Scalar) {
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = res[i][j] + ((Scalar) other).getValue();
                }
            }
            return new Matrix(res);
        } else if (other instanceof Matrix) {
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = res[i][j] + ((Matrix) other).value2[i][j];
                }
            }
            return new Matrix(res);
        }
        return super.add(other);
    }
    @Override
    public Var mul(Var other) {
        if (other instanceof Matrix) {
            double[][] result = new double[value2.length][((Matrix) other).value2[0].length];
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[0].length; j++) {
                    double resultIJ = 0;
                    for (int k = 0; k < value2[0].length; k++) {
                        resultIJ += value2[i][k] * ((Matrix) other).value2[k][j];
                    }
                    result[i][j] = resultIJ;
                }
            }
            return new Matrix(result);
        } else
        if (other instanceof Scalar) {
            double[][] result = new double[value2.length][value2[0].length];
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[0].length; j++) {
                    result[i][j] = value2[i][j] * ((Scalar) other).getValue();
                }
            }
            return new Matrix(result);
        } else if (other instanceof Vector) {
            double[] result = new double[value2[0].length];
            for (int i = 0; i < value2.length; i++) {
                for (int j = 0; j < value2[0].length; j++) {
                    result[i]+=value2[i][j] * ((Vector) other).getValues()[j];
                }
            }
            return new Vector(result);
        }


        return super.mul(other);
    }

    @Override
    public Var div(Var other) {
        if (other instanceof Scalar) {
            double[][] result = new double[value2.length][value2[0].length];
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[0].length; j++) {
                    result[i][j]= value2[i][j]/((Scalar) other).getValue();

                }
            } return new Matrix(result);
        }
        return super.div(other);
    }

    @Override
    public Var sub(Var other) {
        double[][] res = new double[value2.length][value2[0].length];
        for (int i = 0; i < value2.length; i++) {
            for (int j = 0; j < value2[i].length; j++) {
                res[i][j] = value2[i][j];
            }
        }
        if (other instanceof Scalar) {
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = res[i][j] - ((Scalar) other).getValue();
                }
            }
            return new Matrix(res);
        } else if (other instanceof Matrix) {
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = (res[i][j] - ((Matrix) other).value2[i][j]);
                }
            }
            return new Matrix(res);
        }
        return super.add(other);
    }





    @Override
    public String toString() {
        String strMatrix = new String();
        for (int i = 0; i < value2.length; i++) {
            strMatrix += "{";
            for (int j = 0; j < value2.length; j++) {
                strMatrix += Double.toString(value2[i][j]);
                if (j != value2.length - 1) {
                    strMatrix += ", ";
                }
            }
            strMatrix = strMatrix + "}";
            if (i != value2.length - 1) {
                strMatrix += ", ";
            }
        }
        return  "{"+strMatrix.toString()+"}";
    }
}