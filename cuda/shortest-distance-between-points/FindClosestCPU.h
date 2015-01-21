#pragma once

#include <iostream>
#include <ctime>
#include <cuda.h>
#include <cuda_runtime.h>				// Stops underlining of __global__
#include <device_launch_parameters.h>	// Stops underlining of threadIdx etc.

using namespace std;

void FindClosestCPU(float3* points, int* indices, int count) {
// Base case, if there's 1 point don't do anything
if(count <= 1) return;
 // Loop through every point
for(int curPoint = 0; curPoint < count; curPoint++) {
	// This variable is nearest so far, set it to float.max
	float distToClosest = 3.40282e38f;
	// See how far it is from every other point
	for(int i = 0; i < count; i++) {
		// Don't check distance to itself
		if(i == curPoint) continue;
		float dist = ((points[curPoint].x - points[i].x) *
			(points[curPoint].x - points[i].x) +
			(points[curPoint].y - points[i].y) *
			(points[curPoint].y - points[i].y) +
			(points[curPoint].z - points[i].z) *
			(points[curPoint].z - points[i].z));
		if(dist < distToClosest) {
			distToClosest = dist;
			indices[curPoint] = i;
			}
		}
	}
}