package main

import (
	"errors"
	"fmt"
	"strings"
)

func main() {
	plants := []PowerPlant{
		PowerPlant{hydro, 300, active},
		PowerPlant{wind, 30, active},
		PowerPlant{wind, 25, inactive},
		PowerPlant{wind, 35, active},
		PowerPlant{solar, 45, unavailable},
		PowerPlant{solar, 40, inactive},
	}
	grid := PowerGrid{300, plants}

	if option, err := menu(); err == nil {

		fmt.Println("")
		switch option {
		case "1":
			grid.generatePlantReport()
		case "2":
			grid.generateGridReport()
		}

	} else {
		fmt.Println(err.Error())
	}

}

func menu() (option string, err error) {
	fmt.Println("1) Generate Power Plant Report")
	fmt.Println("2) Generate Power Grid Report")
	fmt.Println("Please choose an option: ")

	fmt.Scanln(&option)
	if option != "1" && option != "2" {
		err = errors.New("Invalid option selected")
	}

	return
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

type PlantType string

const (
	hydro PlantType = "Hydro"
	wind  PlantType = "Wind"
	solar PlantType = "Solar"
)

type PlantStatus string

const (
	active      PlantStatus = "Active"
	inactive    PlantStatus = "Inactive"
	unavailable PlantStatus = "Unavailable"
)

type PowerPlant struct {
	plantType PlantType
	capacity  float64
	status    PlantStatus
}

type PowerGrid struct {
	load   float64
	plants []PowerPlant
}

func (pg *PowerGrid) generatePlantReport() {
	for index, plant := range pg.plants {
		label := fmt.Sprintf("%s%d", "Plant #", index)
		printReportLabel(label)
		fmt.Printf("%-20s%s\n", "Type: ", plant.plantType)
		fmt.Printf("%-20s%.0f\n", "Capacity: ", plant.capacity)
		fmt.Printf("%-20s%s\n", "Status: ", plant.status)
		fmt.Println("")
	}
}

func printReportLabel(label string) {
	fmt.Println(label)
	fmt.Println(strings.Repeat("-", len(label)))
}

func (pg *PowerGrid) generateGridReport() {
	capacity := 0.
	for _, plant := range pg.plants {
		if plant.status == active {
			capacity += plant.capacity
		}
	}
	printReportLabel("Power Grid Report")

	fmt.Printf("%-20s%.0f\n", "Capacity: ", capacity)
	fmt.Printf("%-20s%.0f\n", "Load: ", pg.load)
	fmt.Printf("%-20s%.1f%%\n", "Utilization: ", pg.load/capacity*100)
}
