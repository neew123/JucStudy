package JUC.CountDownLatchCyclicSemaph;

import lombok.Getter;

public enum CountryEnum {
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");
    @Getter private Integer retCode;
    @Getter private String retMessage;
    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }
    public static CountryEnum forEach_CountryEnum(int index)
    {
        CountryEnum[] values =CountryEnum.values();
        for (CountryEnum countryEnum : values){
            if(index == countryEnum.getRetCode()){
                return countryEnum;
            }
        }
        return null;
    }
}
