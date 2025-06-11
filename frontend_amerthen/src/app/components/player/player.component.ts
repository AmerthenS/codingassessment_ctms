import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PlayerService } from '../../services/player.service';
import { Player } from '../../models/player';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css'],
})
export class PlayerComponent implements OnInit {
  playerForm: FormGroup;
  players: Player[] = [];
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private playerService: PlayerService) {
    this.playerForm = this.fb.group({
      playerId: [null],
      playerName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      jerseyNumber: ['', [Validators.required, Validators.min(0), Validators.max(999)]],
      role: ['', [Validators.required, Validators.pattern('Batsman|Bowler|Keeper|All Rounder')]],
      totalMatches: [null, [Validators.min(0)]],
      teamName: [null, [Validators.maxLength(100)]],
      countryName: [null, [Validators.maxLength(100)]],
      description: [null, [Validators.maxLength(500)]],
    });
  }

  ngOnInit(): void {
    this.loadPlayers();
  }

  loadPlayers(): void {
    this.playerService.getPlayers().subscribe({
      next: (players: Player[]) => {
        console.log('Players loaded:', players);
        this.players = players;
      },
      error: (err: any) => {
        console.error('Load players error:', err);
        this.errorMessage = 'Failed to load players. Please try again.';
        this.logError(this.errorMessage);
      },
    });
  }

  onSubmit(): void {
    if (this.playerForm.valid) {
      const player: Player = this.playerForm.value;
      console.log('Submitting player:', player);
      if (player.playerId) {
        this.playerService.updatePlayer(player.playerId, player).subscribe({
          next: () => {
            this.loadPlayers();
            this.resetForm();
          },
          error: (err: any) => {
            console.error('Update player error:', err);
            this.errorMessage = 'Failed to update player. Please try again.';
            this.logError(this.errorMessage);
          },
        });
      } else {
        this.playerService.createPlayer(player).subscribe({
          next: () => {
            this.loadPlayers();
            this.resetForm();
          },
          error: (err: any) => {
            console.error('Create player error:', err);
            this.errorMessage = 'Failed to create player. Please try again.';
            this.logError(this.errorMessage);
          },
        });
      }
    } else {
      this.playerForm.markAllAsTouched();
      this.errorMessage = 'Please fill out the form correctly.';
      this.logError(this.errorMessage);
    }
  }

  editPlayer(player: Player): void {
    this.playerForm.patchValue(player);
  }

  deletePlayer(playerId: number): void {
    if (confirm('Are you sure you want to delete this player?')) {
      this.playerService.deletePlayer(playerId).subscribe({
        next: (response: string) => {
          console.log('Delete response:', response); 
          this.loadPlayers();
          this.errorMessage = ''; 
        },
        error: (err: any) => {
          console.error('Delete player error:', err);
          this.errorMessage = err.status === 404 ? `Player with ID ${playerId} not found.` : 'Failed to delete player. Please try again.';
          this.logError(this.errorMessage);
        },
      });
    }
  }

  resetForm(): void {
    this.playerForm.reset();
    this.errorMessage = '';
  }

  private logError(message: string): void {
    (window as any).logError?.(message);
  }
}