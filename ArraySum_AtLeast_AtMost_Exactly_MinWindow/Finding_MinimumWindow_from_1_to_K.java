//Not for -ve values and values like 1 billion
import java.util.*;
class MinimumWindow
{
	public static void main(String args[])
	{
		//String source="This is a test string";
		//String target="trest";
		Integer[] source={1,2,10,6,3,1,4,2,7,-4,6,4,2,8,6,1,7,3};
		Integer[] target={1,2,3,4,-4,4};
		minWindowWithMap(source,target);
	}
	public static String minWindow(String source, String target) {
        //String source="This is a test string";
		//String target="trest";
		source=source.toLowerCase();
		target=target.toLowerCase();
		int sLen=source.length();
		int tLen=target.length();

		int[] needToFind=new int[256];
		int[] hasFound=new int[256];
		int i=0,begin,end,count=0,minWindowLength=Integer.MAX_VALUE,windowBegin=0,windowEnd=0;

		for(i=0;i<tLen;i++)
			needToFind[target.charAt(i)]++;

		for(begin=0,end=0;end<sLen;end++)
		{
			//if(needToFind[source.charAt(end)]==0)
			//	continue;
			hasFound[source.charAt(end)]++;
			if(hasFound[source.charAt(end)]<=needToFind[source.charAt(end)])
				count++;
			if(count==tLen)
			{
				while(needToFind[source.charAt(begin)]==0 || hasFound[source.charAt(begin)] > needToFind[source.charAt(begin)])
				{
					hasFound[source.charAt(begin)]--;
					begin++;
				}
				int windowLen=end-begin+1;
				if(windowLen<minWindowLength)
				{
					minWindowLength=windowLen;
					windowBegin=begin;
					windowEnd=end;
				}
			}			
		}
		if(count==tLen)
		{
				return source.substring(windowBegin,windowEnd+1);
		}
		return "";
    }

    public static void minWindow(int[] source, int[] target) {
		int sLen=source.length;
		int tLen=target.length;

		int[] needToFind=new int[256];
		int[] hasFound=new int[256];
		int i=0,begin,end,count=0,minWindowLength=Integer.MAX_VALUE,windowBegin=0,windowEnd=0;

		for(i=0;i<tLen;i++)
			needToFind[target[i]]++;

		for(begin=0,end=0;end<sLen;end++)
		{
			hasFound[source[end]]++;
			if(hasFound[source[end]]<=needToFind[source[end]])
				count++;
			if(count==tLen)
			{
				while(needToFind[source[begin]]==0 || hasFound[source[begin]] > needToFind[source[begin]])
				{
					hasFound[source[begin]]--;
					begin++;
				}
				int windowLen=end-begin+1;
				if(windowLen<minWindowLength)
				{
					minWindowLength=windowLen;
					windowBegin=begin;
					windowEnd=end;
				}
			}			
		}
		if(count==tLen)
		{
			for(i=windowBegin;i<=windowEnd;i++)
				System.out.print(source[i]+" ");
		}		
    }

    public static void minWindowWithMap(Integer[] s, Integer[] c) {
		WindowObject minWindow = null;
                 
                ArrayList<Integer> dic = new ArrayList<Integer>();
                for(int i = 0; i < c.length; i++){
                        dic.add(c[i]);
                }
            
                TreeMap<Integer, Integer> where = new TreeMap<>();
                ArrayList<Integer> indexInWindow = new ArrayList<Integer>();
 
                for(int i = 0; i < s.length; i++){
                        if(dic.contains(s[i])){                               
                                if(where.containsKey(s[i])){
                                	System.out.println("indexInWindow "+indexInWindow.size());
                                        indexInWindow.remove(where.get(s[i]));
                                }
                                System.out.println("s[i],i "+s[i]+" "+i);
                                where.put(s[i],i);
                                indexInWindow.add(i);
 
                                if(indexInWindow.size() == dic.size()){ 
                                        int firstIndex = indexInWindow.get(0);
                                        int lastIndex = indexInWindow.get(indexInWindow.size()-1);
                                        System.out.println("firstIndex "+firstIndex+" lastIndex "+lastIndex);
                                        WindowObject currentWindow = new WindowObject(s, firstIndex, lastIndex);
                                        if(minWindow == null){ 
                                                minWindow = currentWindow;
                                        }else{
                                                if(minWindow.compareTo(currentWindow) > 0){
                                                        minWindow = currentWindow;
                                                }
 
                                        }
 
                                }
 
                        }
                }
 
                System.out.println( minWindow);
        }
 
        static class WindowObject implements Comparable<WindowObject>{
                private Integer[] window;
                private int firstIndex, lastIndex;
 
                public WindowObject(Integer[] s, int firstIndex, int lastIndex){
                        this.window = Arrays.copyOfRange(s, firstIndex, lastIndex+1);
                        this.firstIndex = firstIndex;
                        this.lastIndex = lastIndex;
                }
 
                public Integer[] getWindow(){
                        return this.window;
                }
 
                @Override
                public int compareTo(WindowObject w) {
                        if(this.window.length < w.getWindow().length){
                                return -1;
                        }
                        if(this.window.length > w.getWindow().length){
                                return 1;
                        }
                        return 0;
                }
 
                @Override
                public String toString(){
                        String s = "";
                        for(int i = 0; i < this.window.length; i++){
                                s += this.window[i];
                        }
                        return s+" ["+this.firstIndex+"]["+this.lastIndex+"]";
                }      
 
        }      
}