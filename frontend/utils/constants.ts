// Server-side: Uses BACKEND_URL (Docker internal network: http://backend:8080)
// Client-side: Uses NEXT_PUBLIC_BACKEND_URL (Public URL: http://193.40.157.19:8080)
// This constant is used in API routes (server-side only)
export const backendUrl = process.env.BACKEND_URL ?? "http://localhost:8080";
