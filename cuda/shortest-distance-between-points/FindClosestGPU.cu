#include <iostream>
#include <ctime>
#include <cuda.h>
#include <cuda_runtime.h>				// Stops underlining of __global__
#include <device_launch_parameters.h>	// Stops underlining of threadIdx etc.

using namespace std;

__global__ void FindClosestGPU(float3* points, int* indices, int count)
{
if(count <= 1) return;

int idx = threadIdx.x + blockIdx.x * blockDim.x;
if(idx < count)
	{
	float3 thisPoint = points[idx];
	float smallestSoFar = 3.40282e38f;
	
	for(int i = 0; i < count; i++)
		{
		if(i == idx) continue;

		float dist = (thisPoint.x - points[i].x)*(thisPoint.x - points[i].x);
		dist += (thisPoint.y - points[i].y)*(thisPoint.y - points[i].y);
		dist += (thisPoint.z - points[i].z)*(thisPoint.z - points[i].z);
		
		if(dist < smallestSoFar)
			{
			smallestSoFar = dist;
			indices[idx] = i;
			}
		}
	}
}

