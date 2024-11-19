export default {
    // config options
    base: "",
    root: "./", // especificamos la carpeta donde esta nuestro index.html
    server: {
        port: 8888,
        strictPort: true,
        // para sincronizar el volumen con lo que tengo en local
        host: '0.0.0.0', // Exponemos el servidor para que sea accesible en la red
        watch: {
            usePolling: true, // Activamos el polling para detectar cambios automáticamente
        },
    },
    build: { // npm run build
        outDir: "./Dist", // Estamos en la carperta donde pone "root:  " y decimos que cree una carpeta /Dist
        emptyOutDir: true,
        minify: false, // para que no se minimice
    },
};