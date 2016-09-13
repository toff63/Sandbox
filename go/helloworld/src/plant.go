package main

import (
	"fmt"
)

func main() {
	menu()

}

func menu() {
	plantCapacities := []float64{30, 30, 30, 60, 60, 100}
	activePlants := []int{0, 1}
	gridLoad := 75.

	fmt.Println("1) Generate Power Plant Report")
	fmt.Println("2) Generate Power Grid Report")
	fmt.Println("Please choose an option: ")

	var option string

	fmt.Scanln(&option)

	switch option {
	case "1":
		powerPlantReport(plantCapacities)
	case "2":
		powerGridReport(plantCapacities, activePlants, gridLoad)
	default:
		fmt.Println("Unknown option.")
	}
}

func powerPlantReport(plantCapacities []float64) {
	for index, capacity := range plantCapacities {
		fmt.Printf("Plant %d capacity: %.0f\n", index, capacity)
	}
}

func powerGridReport(plantCapacities []float64, activePlants []int, gridLoad float64) {
	capacity := 0.
	for _, plantIndex := range activePlants {
		capacity += plantCapacities[plantIndex]
	}

	fmt.Printf("%-20s%.0f\n", "Capacity: ", capacity)
	fmt.Printf("%-20s%.0f\n", "Load: ", gridLoad)
	fmt.Printf("%-20s%.1f%%\n", "Utilization: ", gridLoad/capacity*100)
}
