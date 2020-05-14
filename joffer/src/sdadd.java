


public class sdadd {

  public static void main(String[] args) {
    int[] num = {1,2,3,4,5,6,7,8,9,10};
    int count = 1;
    for(int i=0;i<num.length;i++){
      if(count%7==0){
        System.out.println(num[i]);
        count=1;
      }else{
        System.out.print(num[i]);
        count++;
      }
    }

  }
}
