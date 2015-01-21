#include <iostream>
#include <ctime>
#include <cuda.h>
#include <cuda_runtime.h>				// Stops underlining of __global__
#include <device_launch_parameters.h>	// Stops underlining of threadIdx etc.

#include "FindClosestCPU.h"
#include "FindClosestGPU.h"

using namespace std;

int main()
{
// Number of points
const int count = 10000;

// Arrays of points
int *indexOfClosest = new int[count];
float3 *points = new float3[count];
float3* d_points;	 // GPU version
int* d_indexOfClosest;

// Create a list of random points
for(int i = 0; i < count; i++)
	{
	points[i].x = (float)((rand()%10000) - 5000);
	points[i].y = (float)((rand()%10000) - 5000);
	points[i].z = (float)((rand()%10000) - 5000);
	}

cudaMalloc(&d_points, sizeof(float3) * count);
cudaMemcpy(d_points, points, sizeof(float3) * count, cudaMemcpyHostToDevice);
cudaMalloc(&d_indexOfClosest, sizeof(int) * count);

// This variable is used to keep track of the fastest time so far
long fastest = 1000000;

// Run the algorithm 20 times
for(int q = 0; q < 20; q++)
	{
	long startTime = clock();
	
	// Run the algorithm
	//FindClosestCPU(points, indexOfClosest, count);
	
	FindClosestGPU<<<(count / 320)+1, 320>>>(d_points, d_indexOfClosest, count);
	cudaMemcpy(indexOfClosest, d_indexOfClosest, sizeof(int) * count, cudaMemcpyDeviceToHost);

	long finishTime = clock();
	
	cout<<q<<" "<<(finishTime - startTime)<<endl;

	// If that run was faster update the fastest time so far
	if((finishTime - startTime) < fastest)
		fastest = (finishTime - startTime);
	}

// Print out the fastest time
cout<<"Fastest time: "<<fastest<<endl;

// Print the final results to screen
cout<<"Final results:"<<endl;
for(int i = 0; i < 10; i++)
	cout<<i<<"."<<indexOfClosest[i]<<endl;
	
// Deallocate ram
delete[] indexOfClosest;
delete[] points;
cudaFree(d_points);
cudaFree(d_indexOfClosest);

cudaDeviceReset();

return 0;
}