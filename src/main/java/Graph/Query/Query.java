package Graph.Query;


public class Query<K> {


    public Query(){

    }
    public void select(String... fields){


        for(String param : fields){
           System.out.println(param);
        }
    }

}
