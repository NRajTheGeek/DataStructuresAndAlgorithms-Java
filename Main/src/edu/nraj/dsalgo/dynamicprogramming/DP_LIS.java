package edu.nraj.dsalgo.dynamicprogramming;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DP_LIS {
//
//	/**
//	 * returns the longest increasing subequence (LIS) of input array.
//	 * @param input : problem array
//	 * @return int[]
//	 */
//	public static int[] lis(int[] input) {
//		int len = input.length;
//		int[] each = new int[len];	
//
//		// bufffer array for lis array of each element in input array
//		int[][] lisElem = new int[len][];
//
//		// lenght of maximum length lis 
//		int max = 0;
//
//		// index of maximum length lis in lisElem
//		int maxInd = -1;
//
//		for(int i = 0; i < len; i++) {
//			// capturing lis of each entry
//			each = _lis(input, i);
//
//			// fetching true length lis i.e. length with 0's excluded
//			int lisLength = getTrueLength(each);
//
//			// storing actual lis entries i.e. non-zero entries
//			if(lisLength < 0) {
//				int[] gbg = {0};
//				lisElem[i] = gbg;
//			} else {
//				lisElem[i] = new int[lisLength];
//				for(int j = 0; j < lisLength; j++) {
//					lisElem[i][j] = each[j];
//				}
//			}
//		}
//		
//		maxInd = findMaxLengthInd(lisElem, len);
//
//		return lisElem[maxInd];		
//	}
//
//	/**
//	 * actual lis calculation for each element
//	 * @param input : problem array
//	 * @param elemInd : index of each element to find its lis
//	 * @return int[]
//	 */
//	private static int[] _lis(int[] input, int elemInd) {
//
//		int localMaximum = input[elemInd];
//		int count = 1;
//		int[] output = new int[input.length];
//		output[0] = input[elemInd];
//		
//		
//		for(int i = elemInd + 1, j = 1; i < input.length; i++) {
//			if(localMaximum < input[i]) {
//				localMaximum = input[i];
//				output[j] = localMaximum;
//				j++;
//				count++;
//			}
//		}
//		return output;		
//	}
//
//	/**
//	 * getting true length of lis
//	 * @param input
//	 * @return int
//	 */
//	private static int getTrueLength(int[] input) {
//		int i=0;
//		while(i < input.length) {
//			if(input[i]==0) {
//				break;
//			}
//			i++;
//		}
//		return i;
//	}
//
//	/**
//	 * get the maxImum length index
//	 * @param input : 2D array
//	 * @param len : lenth if the outer array
//	 * @return int
//	 */
//	private static int findMaxLengthInd(int[][] input, int len) {
//		int max = 0;
//		int maxInd = 0;
//		for(int i = 0; i < len; i++) {
//			if(max < input[i].length) {
//				max = input[i].length;
//				maxInd = i;
//			}
//		}
//		return maxInd;
//	}
	
	
	static int lis(int arr[],int n) 
	{ 
		int lis[] = new int[n]; 
		int i,j,max = 0; 

		/* Initialize LIS values for all indexes */
		for ( i = 0; i < n; i++ ) 
			lis[i] = 1; 

		/* Compute optimized LIS values in bottom up manner */
		for ( i = 1; i < n; i++ ) 
			for ( j = 0; j < i; j++ ) 
						if ( arr[i] > arr[j] && lis[i] < lis[j] + 1) 
					lis[i] = lis[j] + 1; 

		/* Pick maximum of all LIS values */
		for ( i = 0; i < n; i++ ) 
			if ( max < lis[i] ) 
				max = lis[i]; 

			return max; 
	} 

	/**
	 * main method
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Reader scan = new Reader();
		int N = scan.readInt();

		int[] input = new int[N];
		
		for(int i = 0; i < N; i++) {
			input[i] = scan.readInt();
		}
//		int[] lisArray = lis(input);
//		System.out.println("length of lis is: " + lisArray.length);
//		
//		for(int i =0; i < lisArray.length; i++) {
//			System.out.print(lisArray[i] + " ");
//		}
		
		System.out.println("length of lis is: " + lis(input, input.length));

	}
	
	static class Reader
	{
		final private int BUFFER_SIZE = 1 << 16; // 65536 bytes = 64 KB bucket
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader()
		{
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE]; // 64 KB buffer
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException
		{
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException
		{
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1)
			{
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int readInt() throws IOException
		{
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do
			{
				ret = ret * 10 + c - '0';
			}  while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long readLong() throws IOException
		{
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			}
			while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double readDouble() throws IOException
		{
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			}
			while ((c = read()) >= '0' && c <= '9');

			if (c == '.')
			{
				while ((c = read()) >= '0' && c <= '9')
				{
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException
		{
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException
		{
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException
		{
			if (din == null)
				return;
			din.close();
		}
	}
}
