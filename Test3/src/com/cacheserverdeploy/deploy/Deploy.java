package com.cacheserverdeploy.deploy;


public class Deploy
{
    /**
     * todo
     * 
     * @param graphContent caseinfo
     * @return  caseouput info
     * @see [huawei]
     */
    public static String[] deployServer(String[] graphContent)
    {
        /**do your work here**/
    	String[] result = Graph.execute(graphContent);
    	
        return result;
    }

}
