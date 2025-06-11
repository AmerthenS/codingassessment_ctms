import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Player } from '../models/player';

@Injectable({
  providedIn: 'root',
})
export class PlayerService {
  private apiUrl = 'http://localhost:8080/api/players';

  constructor(private http: HttpClient) {}

  getPlayers(): Observable<Player[]> {
    return this.http.get<Player[]>(this.apiUrl).pipe(catchError(this.handleError));
  }

  getPlayer(playerId: number): Observable<Player> {
    return this.http.get<Player>(`${this.apiUrl}/${playerId}`).pipe(catchError(this.handleError));
  }

  createPlayer(player: Player): Observable<Player> {
    return this.http.post<Player>(this.apiUrl, player).pipe(catchError(this.handleError));
  }

  updatePlayer(playerId: number, player: Player): Observable<Player> {
    return this.http.put<Player>(`${this.apiUrl}/${playerId}`, player).pipe(catchError(this.handleError));
  }

  deletePlayer(playerId: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${playerId}`, { responseType: 'text' }).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An error occurred. Please try again.';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Client error: ${error.error.message}`;
    } else {
      errorMessage = `Server error: ${error.status} - ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }
}