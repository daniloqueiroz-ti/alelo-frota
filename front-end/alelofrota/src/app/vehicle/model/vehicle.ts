export interface VehiclePage {
    content: Vehicle[];
    totalElements: number;    
}

export interface Vehicle {
    id: number;
    plate: string;
    model: string;
    manufacturer: string;
    color:string;
    status: boolean;
}