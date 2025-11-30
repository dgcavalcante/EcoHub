import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private base = environment.apiUrl;

  constructor(private http: HttpClient) {}

  // User endpoints
  login(email: string, senha: string): Observable<any> {
    const url = `${this.base}/users/login?email=${encodeURIComponent(email)}&senha=${encodeURIComponent(senha)}`;
    // Backend returns a plain string on success (text/plain). By default
    // HttpClient expects JSON and will throw a parse error. Request as
    // text to avoid JSON parse errors and let the caller handle the string.
    return this.http.post<any>(url, {}, { responseType: 'text' as 'json' });
  }

  registerUser(body: any): Observable<any> {
    return this.http.post(`${this.base}/users/cadastro`, body);
  }

  recoverPassword(body: any): Observable<any> {
    return this.http.post(`${this.base}/users/recuperar-senha`, body);
  }

  updateUser(cpf: string, body: any): Observable<any> {
    return this.http.put(`${this.base}/users/atualizar?cpf=${encodeURIComponent(cpf)}`, body);
  }

  updatePassword(email: string, body: any): Observable<any> {
    return this.http.put(`${this.base}/users/atualizar/${encodeURIComponent(email)}`, body);
  }

  deleteUser(id: string): Observable<any> {
    return this.http.delete(`${this.base}/users/deletar/${id}`);
  }

  listarUsuarios(): Observable<any> {
    return this.http.get(`${this.base}/users/listarTodos`);
  }

  // Dispositivo endpoints
  listarDispositivos(): Observable<any> {
    return this.http.get(`${this.base}/dispositivo/listar/todos`);
  }

  cadastrarDispositivo(body: any): Observable<any> {
    return this.http.post(`${this.base}/dispositivo/cadastro`, body);
  }

  listarDispositivoPorId(id: string): Observable<any> {
    return this.http.get(`${this.base}/dispositivo/listar/${id}`);
  }

  deleteDevice(id: string): Observable<any> {
    return this.http.delete(`${this.base}/dispositivo/deletar/${id}`);
  }

  injetarDadosConsumo(id: string, mes: number, horas: number): Observable<any> {
    return this.http.post(`${this.base}/dispositivo/injetarDados/${id}/${mes}/${horas}`, {});
  }

  relatorioTotal(id: string): Observable<any> {
    return this.http.get(`${this.base}/dispositivo/relatorioTotal/${id}`);
  }

  relatorioMensal(id: string, mes: number): Observable<any> {
    return this.http.get(`${this.base}/dispositivo/relatorioMensal/${id}/${mes}`);
  }

  relatorioPersonalizado(id: string, mesInicial: number, mesFinal: number): Observable<any> {
    return this.http.get(`${this.base}/dispositivo/relatorioPersonalizado/${id}/${mesInicial}/${mesFinal}`);
  }

  // Perfil endpoints
  cadastrarPerfil(body: any): Observable<any> {
    return this.http.post(`${this.base}/perfil/cadastro`, body);
  }

  atualizarPerfil(id: string, body: any): Observable<any> {
    return this.http.put(`${this.base}/perfil/atualizar/${id}`, body);
  }

  listarPerfis(): Observable<any> {
    return this.http.get(`${this.base}/perfil/listarTodos`);
  }

  deletarPerfil(id: string): Observable<any> {
    return this.http.delete(`${this.base}/perfil/deletar/${id}`);
  }

  // Helper to extract a readable error message from HttpClient errors
  getErrorMessage(err: any): string {
    if (!err) return 'Erro desconhecido';
    if (typeof err === 'string') return err;
    // HttpErrorResponse often has an `error` field
    const e = err.error ?? err;
    // If it's a simple string
    if (typeof e === 'string') return e;
    // If it's an array of messages
    if (Array.isArray(e)) return e.map((x) => (typeof x === 'string' ? x : JSON.stringify(x))).join(', ');
    if (typeof e === 'object') {
      if (e.message && typeof e.message === 'string') return e.message;
      if (e.errors && Array.isArray(e.errors)) return e.errors.join(', ');
      // try to find any string-valued property
      for (const val of Object.values(e)) {
        if (typeof val === 'string') return val;
        if (Array.isArray(val) && val.length && typeof val[0] === 'string') return val.join(', ');
      }
      try { return JSON.stringify(e); } catch (_) { /* fallthrough */ }
    }
    if (err.message) return err.message;
    try { return String(err); } catch (_) { return 'Erro desconhecido'; }
  }
}
