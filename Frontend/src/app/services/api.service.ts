import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private base = environment.apiUrl;

  constructor(private http: HttpClient) {}

  login(email: string, senha: string): Observable<any> {
    const url = `${this.base}/users/login?email=${encodeURIComponent(email)}&senha=${encodeURIComponent(senha)}`;

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

  getErrorMessage(err: any): string {
    if (!err) return 'Erro desconhecido';
    if (typeof err === 'string') return err;
    const e = err.error ?? err;
    if (typeof e === 'string') return e;
    if (Array.isArray(e)) return e.map((x) => (typeof x === 'string' ? x : JSON.stringify(x))).join(', ');
    if (typeof e === 'object') {
      if (e.message && typeof e.message === 'string') return e.message;
      if (e.errors && Array.isArray(e.errors)) return e.errors.join(', ');
      for (const val of Object.values(e)) {
        if (typeof val === 'string') return val;
        if (Array.isArray(val) && val.length && typeof val[0] === 'string') return val.join(', ');
      }
      try { return JSON.stringify(e); } catch (_) { }
    }
    if (err.message) return err.message;
    try { return String(err); } catch (_) { return 'Erro desconhecido'; }
  }
}
