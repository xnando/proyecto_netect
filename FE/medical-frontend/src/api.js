
import axios from "axios";

// Usa mismo origen (Host: medical.local) si no hay env:
const api = axios.create({
  baseURL: process.env.REACT_APP_API_BASE_URL ||"http://medical.local:31290/api/v1",   // <— evita http://localhost:8080
  headers: { "Cache-Control": "no-store" }
});

// Rompe caché en todos los GET
api.interceptors.request.use((config) => {
  if (config.method === "get") {
    const ts = Date.now();
    config.params = { ...(config.params || {}), _: ts };
  }
  return config;
});

export default api;
