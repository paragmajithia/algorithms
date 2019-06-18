package com.parag.techgig.eighteen.round3;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;
public class CandidateCode2 {

	static int N;

	static int M;

	public static void main(String args[] ) throws Exception {

		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		M = scan.nextInt();
		int [][] edges = new int[N][N];
		long[][] he = new long[N][N];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				edges[i][j] = 0;
			}
		}
		for(int i = 0 ; i < M ; i++) {
			int u = scan.nextInt() - 1;
			int v = scan.nextInt() - 1;
			int t = scan.nextInt();
			int h = scan.nextInt();
			edges[u][v] = 1;
			he[u][v] = h-t;
		}
		int minSteps = 10000;
		long maxLead = 0;
		boolean found = false;
		int [] zx = new int[N];
		Arrays.fill(zx, 0);
		for(int x = 0 ; x < N ; x++) {
			int y = new Double((N-x)*Math.random()).intValue() + 1;
			int iter = 0;
			int tim = 0;
			while(tim!=y) {
				if(zx[iter]==0) {
					tim++;
				}
				iter++;
			}
			int i = iter-1;
			zx[iter-1] = 1;
			int mx = 10000;
			long ml = 0;
			long [] ld = new long[N];
			Arrays.fill(ld, Long.MIN_VALUE);
			BitSet expand = new BitSet(N);
			expand.clear();
			expand.set(i);
			ld[i] = 0;
			int len = 0;
			found = false;
			//long [] max = new long[N];
			//Arrays.fill(max, Long.MIN_VALUE);
			while((!found)&&(len<(N-i))) {
				len++;
				BitSet newExpand = new BitSet(N);
				newExpand.clear();
				long [] nld = new long[N];
				for(int j = 0 ; j < N ; j++) {
					nld[j] = ld[j];
				}
				for(int j = 0 ; j < N ; j++) {
					if(expand.get(j)) {
						int [] esh = edges[j];
						long [] hsh = he[j];
						for(int k = 0 ; k < N ; k++) {
							if(esh[k]==1) {
								if(ld[j]+hsh[k]>nld[k]) {
									nld[k] = ld[j] + hsh[k];
									newExpand.set(k);
								}
							}
						}
					}
				}
				int j = i;
				if(newExpand.get(j)) {
					if(nld[j]>0) {
						// found;
						found = true;
						if(mx==len) {
							ml = Math.max(ml, nld[j]);
						} else {
							mx = len;
							ml = nld[j];
						}
					}
				}
				expand = newExpand;
				ld = nld;
				if(found) {
					break;
				}
			}
			if(minSteps>mx) {
				minSteps = mx;
				maxLead = ml;
			} else if(minSteps==mx) {
				maxLead = Math.max(maxLead, ml);
			}
		}
		System.out.println(minSteps+ " " + maxLead);
		//System.out.println(maxLead);
	}
}