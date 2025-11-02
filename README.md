# Janji

Saya Mohammad Mudrik dengan NIM 2407142 berjanji untuk mengerjakan tugas praktikum 6 dengan sungguh-sungguh Dalam mata kuliah Dasar Pemrograman Berorientasi Objek tanpa membocorkan atau mencotek kepada teman Demi Kebaikan-Nya

# Desain Program

1. App.java
   
File ini menjadi tempat pertama saat program dijalankan.
  - Menampilkan menu utama dengan dua tombol: Play dan Exit.
  - Tombol Play memulai game dengan membuat objek Logic dan View.
  - Tombol Exit menutup aplikasi.

2. Logic.java

Bagian ini adalah otak permainan. Semua aturan, pergerakan, dan skor diatur di sini.
  - Mengatur posisi pemain dan pipa.
  - Mengatur gravitasi pemain, gerakan pipa, serta deteksi tabrakan.
  - Jika pemain menabrak atau jatuh, status gameOver jadi true dan permainan berhenti.
  - Menambah skor saat pemain melewati pipa.
  - Menerima input keyboard:
    - SPACE untuk lompat.
    - R untuk restart setelah game over.
  - resetGame() mengulang semua kondisi ke awal.

3. Player.java

Kelas ini menyimpan data tentang (pemain).   
  - Menyimpan posisi, ukuran, gambar, dan kecepatan jatuh.
  - Hanya berisi getter dan setter (tidak punya logika gerak).
    
4. Pipe.java

Kelas ini menyimpan data tentang pipa.
  - Menyimpan posisi, ukuran, gambar, dan status apakah sudah dilewati pemain atau belum.
  - Hanya berisi getter dan setter (tidak punya logika sendiri).

5. View.java

Kelas ini menggambar tampilan game di layar.
  - Menggunakan JPanel untuk menggambar pemain, pipa, dan latar.
  - Menampilkan skor lewat JLabel di bagian atas.
  - Jika gameOver, menampilkan tulisan “GAME OVER” dan “Press R to Restart”.
  - Mengirim input tombol ke Logic supaya bisa diproses.

# Alur Program

1. Awal Program (App.java)
  - Program dimulai dari main() di App.java.
  - Menu utama tampil di tengah layar dengan dua tombol:
    - Play Flappy Bird → memulai permainan.
    - Exit Program → menutup aplikasi.
  - Saat tombol Play ditekan:
    - Metode startGame() dijalankan.
    - Membuat objek Logic (pengendali game) dan View (tampilan).
    - Logic dan View dihubungkan.
    - Menu utama ditutup, dan jendela game ditampilkan.

2. Inisialisasi Game (Logic.java)
  - Saat Logic dibuat:
    - Gambar (burung dan pipa) dimuat.
    - Objek Player dibuat di posisi awal.
  - Dua Timer langsung dijalankan:
    - gameLoop: memperbarui game 60 kali per detik.
    - pipesCooldown: membuat pipa baru setiap 2 detik.

3. Jalannya Game (Game Loop)
  - Setiap tick (sekitar 1/60 detik):
    - Logic.move() dijalankan:
      - Posisi Player berubah karena gravitasi.
      - Pipe bergerak ke kiri.
    - Jika Player berhasil melewati pipa, skor bertambah 1.
    - View.repaint() menggambar ulang tampilan.
  - Setiap 2 detik:
    - Logic.placePipes() membuat pasangan pipa baru di sisi kanan layar.

4. Saat Game Over
  - Game over terjadi jika:
    - Player menabrak pipa, atau
    - Player jatuh ke bawah layar.
  - Jika itu terjadi:
    - gameOver = true.
    - Timer pembuatan pipa dihentikan.
    - View menampilkan tulisan “GAME OVER” dan “Press R to Restart”.

5. Restart Game
  - Setelah Game Over, jika pemain menekan tombol R:
    - resetGame() dijalankan.
    - Posisi Player dikembalikan ke awal.
    - Semua pipa dihapus.
    - Skor direset ke 0.
    - Timer dijalankan kembali.
  - Game dimulai lagi dari awal tanpa perlu menutup program.

# Dokumentasi

https://github.com/user-attachments/assets/261dc9b3-2bc8-438c-ae61-81c4a25b7590




