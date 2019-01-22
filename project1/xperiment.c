#include <stdio.h>
#include "array_util.h"
#include "sorts.h"
#include "math.h"



int
main() {

///*For question 1

  int i,vor,nach;
  int arr[1000000];
  int arr2[1000000];
  int base = 1000;
  random_array(arr,1000000);
  printf("Size\tTime\n");
  for (i=1;i<=50;i++){
  	copy_array(arr,arr2,base*i);
	vor = get_time_millis();
  	selectionSort(arr2,base*i);
	nach = get_time_millis();
  	printf("%d\t%d\n",base*i,nach-vor);
  }
  printf("\n");
  for (i=1;i<=50;i++){
  	copy_array(arr,arr2,base*i);
	vor = get_time_millis();
  	insertionSort(arr2,base*i);
	nach = get_time_millis();
  	printf("%d\t%d\n",base*i,nach-vor);
  }
  printf("\n");
  for (i=1;i<=50;i++){
  	copy_array(arr,arr2,base*i);
	vor = get_time_millis();
  	bubbleSort(arr2,base*i);
	nach = get_time_millis();
  	printf("%d\t%d\n",base*i,nach-vor);
  }
  printf("\n");
  for (i=1;i<=50;i++){
  	copy_array(arr,arr2,base*i);
	vor = get_time_millis();
  	quickSort(arr2,base*i);
	nach = get_time_millis();
  	printf("%d\t%d\n",base*i,nach-vor);
  }
  printf("\n");
  for (i=1;i<=50;i++){
  	copy_array(arr,arr2,base*i);
	vor = get_time_millis();
  	shellSort(arr2,base*i);
	nach = get_time_millis();
  	printf("%d\t%d\n",base*i,nach-vor);
  }
  printf("\n");
  for (i=1;i<=50;i++){
  	copy_array(arr,arr2,base*i);
	vor = get_time_millis();
  	mergeSort(arr2,base*i);
	nach = get_time_millis();
  	printf("%d\t%d\n",base*i,nach-vor);
  }
}

//End question 1 */

/* Start question 2/3a

int vor,nach;
int arr[1000000];
int arr2[1000000];
random_array(arr,1000000);
int i=0;
int x=0;
int p=10;
while (x <p) {
	copy_array(arr,arr2,i);
	vor = get_time_millis();
  	selectionSort(arr2,i);
	nach = get_time_millis();
	x = nach-vor;
	i++;
}
printf("selectionSort:\t%d\n",i);
x=0;
i=0;
while (x <p) {
	copy_array(arr,arr2,i);
	vor = get_time_millis();
  	insertionSort(arr2,i);
	nach = get_time_millis();
	x = nach-vor;
	i++;
}
printf("insertionSort:\t%d\n",i);
x=0;
i=0;
while (x <p) {
	copy_array(arr,arr2,i);
	vor = get_time_millis();
  	bubbleSort(arr2,i);
	nach = get_time_millis();
	x = nach-vor;
	i++;
}
printf("bubbleSort:\t%d\n",i);
x=0;
i=0;
while (x <p) {
	copy_array(arr,arr2,i);
	vor = get_time_millis();
  	quickSort(arr2,i);
	nach = get_time_millis();
	x = nach-vor;
	i++;
}
printf("quickSort:\t%d\n",i);
x=0;
i=0;
while (x <p) {
	copy_array(arr,arr2,i);
	vor = get_time_millis();
  	shellSort(arr2,i);
	nach = get_time_millis();
	x = nach-vor;
	i++;
}
printf("shellSort:\t%d\n",i);
x=0;
i=0;
while (x <p) {
	copy_array(arr,arr2,i);
	vor = get_time_millis();
  	mergeSort(arr2,i);
	nach = get_time_millis();
	x = nach-vor;
	i++;
}
printf("mergeSort:\t%d\n",i);
}

End question 2/3a */

/* Start question 2/3b

int vor,nach;
int arr[1000000];
int arr2[1000000];
random_array(arr,1000000);
int a=0;
int b=0;
int i=15000;
while (b<=a) {
	copy_array(arr,arr2,i);
	vor = get_time_millis();
  	quickSort(arr2,i);
	nach = get_time_millis();
	a = nach-vor;
	copy_array(arr,arr2,i);
	vor = get_time_millis();
	shellSort(arr2,i);
	nach = get_time_millis();
	b = nach-vor;
	i++;
	}
printf("quickSort:\t%d\nshellSort:\t%d\nElement:%d\n",a,b,i);
}

End question 2/3b */ 