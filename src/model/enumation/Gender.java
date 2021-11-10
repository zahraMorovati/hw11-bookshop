package model.enumation;

public enum Gender {
    MALE(0,"male","مرد"),
    FEMALE(1,"female","زن");

    int index;
    String name;
    String persianName;

    Gender(int index, String name, String persianName) {
        this.index=index;
        this.name=name;
        this.persianName=persianName;
    }

    public Gender getVal(String str){
        if(str.equalsIgnoreCase(MALE.name)){
            return MALE;
        }else if(str.equalsIgnoreCase(FEMALE.name)){
            return FEMALE;
        }
        return null;
    }
}
