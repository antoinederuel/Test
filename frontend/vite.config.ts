import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,      // Force le port 5173
    strictPort: true, // Si 5173 est pris, Vite s'arrête au lieu de passer à 5174
  },
})