package com.parag.techgig.eighteen.round3;

import java.util.Arrays;
import java.util.Scanner;
public class CandidateCode {

	public class Point implements Comparable<Point> {
		int x;
		int y;
		public Point ( int xx , int yy) {
			x = xx;
			y = yy;
		}
		public int compareTo(Point p) {
			int comp = new Integer(y).compareTo(p.y);
			if(comp!=0) {
				return comp;
			}
			return new Integer(x).compareTo(p.x);
		}
	}

	static int nc;

	static int nb;

	static int N;

	static Point [] pc;

	static Point [] pb;

	static CandidateCode main;



	public static void main(String args[] ) throws Exception {
		main = new CandidateCode();
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		nc = 0;
		nb = 0;
		pc = new Point[1000];
		pb = new Point[1000];
		for(int i = 0 ; i < N ; i++) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			String c = scan.next();
			if(c.charAt(0)=='C') {
				pc[nc++] = main.new Point(x,y);
			} else {
				pb[nb++] = main.new Point(x,y);
			}
		}
		Arrays.sort(pc,0,nc);
		Arrays.sort(pb,0,nb);
		int maxCount = 0;
		int minArea = 0;
		for(int i = 0 ; i < nc ; i++) {
			for(int j = 0 ; j < nc ; j++) {
				Point p1 = pc[i];
				Point p2 = pc[j];
				if(p1.x>p2.x) {
					Point p = p1;
					p1 = p2;
					p2 = p;
				}

				int sy = -1;
				int ic = 0;
				int ib = 0;

				int count = 0;
				int area = 0;
				int lastB = -1;
				while((ic<nc)&&(ib<nb)) {
					int yc = pc[ic].y;
					int yb = pb[ib].y;
					if(yc<yb) {
						if((pc[ic].x>=p1.x)&&(pc[ic].x<=p2.x)&&(yc!=lastB)) {
							if(count==0) {
								sy = yc;
								area = 0;
							} else {
								area = ((p2.x-p1.x)*(yc-sy));
							}
							count++;

							if(count>maxCount) {
								maxCount = count;
								//System.out.println("count = " + count + "maxCount = " + maxCount + "area = " + area + "minArea = " + minArea);
								minArea = area;
							} else if(count==maxCount) {
								//System.out.println("count = " + count + "maxCount = " + maxCount + "area = " + area + "minArea = " + minArea);
								if(area<minArea) {
									minArea = area;
								}
							}
						}
						ic++;
					} else {
						if((pb[ib].x>=p1.x)&&(pb[ib].x<=p2.x)) {
							count = 0;
							lastB = pb[ib].y;
						}
						ib++;
					}
				}
				while(ic<nc) {
					int yc = pc[ic].y;
					if((pc[ic].x>=p1.x)&&(pc[ic].x<=p2.x)&&(yc!=lastB)) {
						
						if(count==0) {
							sy = yc;
							area = 0;
						} else {
							area = ((p2.x-p1.x)*(yc-sy)); 
						}
						count++;
						if(count>maxCount) {
							//System.out.println("1. count = " + count + "maxCount = " + maxCount + "area = " + area + "minArea = " + minArea);

							maxCount = count;
							minArea = area;
						} else if(count==maxCount) {
							//System.out.println("2. count = " + count + "maxCount = " + maxCount + "area = " + area + "minArea = " + minArea);

							if(area<minArea) {
								minArea = area;
							}
						}
					}
					ic++;
				}

			}
		}
		System.out.println(maxCount);
		System.out.println(minArea);
	}
}
