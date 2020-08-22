package helperClasses;

import java.math.BigInteger;

public class BigNumber implements Comparable {
    String value;

    public BigNumber(String value) {
        this.value = value;
    }

    //addin two BigNumber
    public BigNumber add(BigNumber bigNumber) {

        if(this.value.trim().length() > bigNumber.value.trim().length()) {
            this.value = "0" + this.value.trim();
            for (int i=0 ; i<this.value.length() - bigNumber.value.length() + 2 ; i++)
                bigNumber.value = "0" + bigNumber.value.trim();
        }

        else if(this.value.trim().length() == bigNumber.value.trim().length()) {
            bigNumber.value = "0" + bigNumber.value.trim();
            this.value = "0" + this.value.trim();
        }

        else {
            bigNumber.value = "0" + bigNumber.value.trim();
            for (int i=0 ; i<bigNumber.value.length() - this.value.length() + 2 ; i++)
                this.value = "0" + this.value.trim();
        }

        int carry = 0;
        int temp  = 0;
        String result = "";
        int pointer = this.value.length() - 1;
        while(pointer != -1) {
            temp = addDigits(this.value.substring(pointer, pointer+1), bigNumber.value.substring(pointer, pointer+1));
            result = String.valueOf((temp % 10) + carry ) + result;
            carry = temp/10;
            pointer --;
        }

        for (int i=0 ; i<this.value.length() ; i++){
            if(result.substring(0,1).equals("0"))
                result = result.substring(1);
        }

        return new BigNumber(result);
    }

    private int addDigits(String digintA, String digitB) {
        return Integer.valueOf(digitB) + Integer.valueOf(digintA);
    }

    //return 1 if value > object;
    //return 0 if value = object
    @Override
    public int compareTo(Object o) {

        //comparing lengths
        if (!(o instanceof BigNumber))
            throw new IllegalArgumentException("entery object is not BigNumber");
        else {
            BigNumber bigNumber = (BigNumber) o;
            if (value.trim().length() > bigNumber.value.trim().length())
                return 1;
            else if (value.trim().length() < bigNumber.value.trim().length())
                return -1;
            else {

                //if lengthes are equals
                for (int i = 0; i < value.trim().length() ; i++) {
                    int thisDigit = Integer.valueOf(value.trim().substring(i, i + 1));
                    int bigNumDigit = Integer.valueOf(bigNumber.value.trim().substring(i, i + 1));

                    if (thisDigit > bigNumDigit)
                        return 1;
                    else if (thisDigit < bigNumDigit)
                        return -1;
                    else continue;
                }

                return 0;
            }
        }

    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return this.compareTo(obj) == 0;
    }


//    public static void main(String[] args) {
//        System.out.println(new BigNumber("1").add(new BigNumber("1")));
//    }

}
