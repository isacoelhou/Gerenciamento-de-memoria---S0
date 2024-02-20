import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class LRU {

    public static void main(String[] args) {
        int[] frameSizes = {4, 8, 16, 32}; // Diferentes tamanhos de frames
        String referenceStringFile = "reference_string.txt"; // Arquivo de entrada
        
        for (int frameSize : frameSizes) {
            try {
                int pageFaults = lruPageReplacement(referenceStringFile, frameSize);
                System.out.println("Para o tamanho do frame " + frameSize + ", o número de falhas de página é: " + pageFaults);
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo de entrada: " + e.getMessage());
            }
        }
    }

    public static int lruPageReplacement(String filename, int frameSize) throws IOException {
        Deque<String> frameQueue = new ArrayDeque<>();
        Map<String, Integer> pageLastUsedMap = new HashMap<>();
        int pageFaults = 0;

        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        
        while ((line = reader.readLine()) != null) {
            String pageHex = line.trim();

            // Se a página já está na memória, atualize o tempo em que foi usada
            if (pageLastUsedMap.containsKey(pageHex)) {
                frameQueue.remove(pageHex);
                frameQueue.addLast(pageHex);
                pageLastUsedMap.put(pageHex, frameQueue.size());
                continue;
            }

            // Se ainda houver espaço nos frames, apenas adicione a página
            if (frameQueue.size() < frameSize) {
                frameQueue.addLast(pageHex);
                pageLastUsedMap.put(pageHex, frameQueue.size());
            } else {
                // Caso contrário, remova a página menos recentemente usada (LRU)
                String removedPage = frameQueue.removeFirst();
                pageLastUsedMap.remove(removedPage);

                // Adicione a nova página
                frameQueue.addLast(pageHex);
                pageLastUsedMap.put(pageHex, frameQueue.size());
                pageFaults++;
            }
        }
        
        reader.close();
        return pageFaults;
    }
}
