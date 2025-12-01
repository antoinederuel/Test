# --- Étape 1 : Le Builder (Node.js) ---
# On utilise une image Node juste pour construire le projet
FROM node:20-alpine as builder

WORKDIR /app

# On copie d'abord les dépendances (optimisation cache Docker)
COPY package*.json ./
RUN npm ci

# On copie le reste et on build
COPY . .
RUN npm run build

# --- Étape 2 : Le Runner (Nginx) ---
# On part d'une image Nginx très légère
FROM nginx:alpine

# On supprime la config par défaut de Nginx
RUN rm /etc/nginx/conf.d/default.conf

# On copie NOTRE config Nginx
COPY nginx.conf /etc/nginx/conf.d

# On copie les fichiers "buildés" (le dossier dist) de l'étape 1 vers le dossier de Nginx
COPY --from=builder /app/dist /usr/share/nginx/html

# On expose le port 80 (standard Web)
EXPOSE 80

# On lance Nginx
CMD ["nginx", "-g", "daemon off;"]