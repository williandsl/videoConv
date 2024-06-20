//package org.example;
//
//import com.github.kiulian.downloader.YoutubeDownloader;
//import com.github.kiulian.downloader.downloader.request.RequestVideoFileDownload;
//import com.github.kiulian.downloader.downloader.request.RequestVideoInfo;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//public class YouTubeService {
//
//    private final YoutubeDownloader downloader;
//
//    public YouTubeService() {
//        this.downloader = new YoutubeDownloader();
//    }
//
//    public void downloadVideo(String videoUrl, String outputDirectory, String fileName) throws IOException {
//        String videoId = "pq2JKVI6F5s"; // for url https://www.youtube.com/watch?v=abc12345
//
//
//        RequestVideoFileDownload request = new
//                RequestVideoFileDownload(videoId);
//
//        try {
//            List<VideoInfo> videoInfos = (List<VideoInfo>) downloader.getVideoInfo(request);
//
//            if (videoInfos.isEmpty()) {
//                throw new IllegalArgumentException("Nenhum vídeo encontrado para o URL fornecido: " + videoUrl);
//            }
//
//            VideoInfo video = videoInfos.get(0); // Supondo que estamos baixando apenas o primeiro vídeo da lista
//
//            File outputDir = new File(outputDirectory);
//            if (!outputDir.exists()) {
//                outputDir.mkdirs();
//            }
//
//            File outputFile = new File(outputDirectory, fileName + "." + video.getVideoId());
//
//            downloader.downloadVideoFile(request);
//            System.out.println("Vídeo baixado com sucesso: " + outputFile.getAbsolutePath());
//        } catch (IOException e) {
//            System.err.println("Erro ao baixar o vídeo: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void main(String[] args) {
//        YouTubeService service = new YouTubeService();
//        String videoUrl = "https://www.youtube.com/watch?v=VIDEO_ID"; // Substitua pelo URL do vídeo
//        String outputDirectory = "downloads";
//        String fileName = "video";
//
//        try {
//            service.downloadVideo(videoUrl, outputDirectory, fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
