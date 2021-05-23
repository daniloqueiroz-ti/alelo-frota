import { VehicleService } from './../service/vehicle-service';
import { Vehicle, VehiclePage } from './../model/vehicle';
import { DataSource } from '@angular/cdk/table';
import { CollectionViewer } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from "rxjs";
import { catchError, finalize } from "rxjs/operators";

export class TodoDataSource implements DataSource<Vehicle>{

    private todoSubject = new BehaviorSubject<Vehicle[]>([]);
    private loadingSubject = new BehaviorSubject<boolean>(false);
    private countSubject = new BehaviorSubject<number>(0);
    public counter$ = this.countSubject.asObservable();

    constructor(private todoService: VehicleService) { }

    connect(collectionViewer: CollectionViewer): Observable<Vehicle[]> {
        return this.todoSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.todoSubject.complete();
        this.loadingSubject.complete();
        this.countSubject.complete();
    }

    loadByFilters(filter: string, pageNumber = 0, pageSize = 10) {
        this.loadingSubject.next(true);
        this.todoService.getLista({ filter: filter, page: pageNumber, size: pageSize })
            .pipe(
                catchError(() => of([])),
                finalize(() => this.loadingSubject.next(false))
            )
            .subscribe((result: any) => {
                this.todoSubject.next(result.content);
                this.countSubject.next(result.totalElements);
            }
            );
    }

}