import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap, switchMap, map, of } from 'rxjs';
import { ApiService } from './api.service';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private currentUserSubject = new BehaviorSubject<any>(this.loadUser());
  currentUser$ = this.currentUserSubject.asObservable();

  constructor(private api: ApiService) {}

  private loadUser() {
    try { return JSON.parse(localStorage.getItem('currentUser') || 'null'); } catch { return null; }
  }

  get currentUser() { return this.currentUserSubject.value; }

  login(email: string, senha: string): Observable<any> {
    return this.api.login(email, senha).pipe(
      // If backend returned a user object (or {user, token}) we'll use it; otherwise
      // backend returns a success string, so fetch the user by email from listarUsuarios()
      switchMap((userOrPayload: any) => {
        const possibleUser = userOrPayload?.user ?? userOrPayload;
        if (possibleUser && typeof possibleUser === 'object') {
          return of(possibleUser);
        }
        // fallback: load users and find by email
        return this.api.listarUsuarios().pipe(
          map((list: any[]) => {
            if (!Array.isArray(list)) return null;
            return list.find((u: any) => u.email === email) ?? null;
          })
        );
      }),
      map((user: any) => {
        // If we couldn't find a user after backend success, treat as error.
        if (!user) throw { error: 'Usuário não encontrado após autenticação' };
        return user;
      }),
      tap((user: any) => {
        const token = null;
        const store = { ...(user || {}), token };
        try { localStorage.setItem('currentUser', JSON.stringify(store)); } catch(_) {}
        this.currentUserSubject.next(store);
      })
    );
  }

  logout() {
    try { localStorage.removeItem('currentUser'); } catch(_) {}
    this.currentUserSubject.next(null);
  }

  setUser(user: any) {
    try { localStorage.setItem('currentUser', JSON.stringify(user)); } catch(_) {}
    this.currentUserSubject.next(user);
  }
}
