package com.alejandrocamino.tema4maven;

import com.github.lalyos.jfiglet.FigletFont;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> lines = new ArrayList<>();
        String banner = FigletFont.convertOneLine("Alejandro Camino");
        Collections.addAll(lines, banner.split("\n"));

        lines.add("");
        lines.add("");
        lines.add("");
        lines.add("================");
        lines.add("Estudiante de programación backend en el IESME");
        lines.add("Recursos de estudio independiente: JetBrains Academy, DataCamp");
        lines.add("Experiencia en:");
        lines.add("- Backend:   Java, Git, Bash, PostgreSQL, NoSQL");
        lines.add("- Frontend:  HTML5, CSS3, JS");
        lines.add("- Ofimática: Excel, Microsoft suite, Canva, Flourish");
        lines.add("Idiomas:");
        lines.add("- Castellano (nativo)");
        lines.add("- Valenciano (nativo)");
        lines.add("- Inglés (B2) - Cambridge");

        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        screen.setCursorPosition(null);

        int terminalHeight = screen.getTerminalSize().getRows();
        int yOffset = terminalHeight;

        while (yOffset > -lines.size()) {
            drawFrame(screen, lines, yOffset);
            Thread.sleep(100);
            yOffset--;
        }

        screen.stopScreen();
    }

    // Lanterna, dibujar en la terminal
    private static void drawFrame(Screen screen, List<String> lines, int yOffset)
            throws IOException {
        TerminalSize size = screen.getTerminalSize();
        int width = size.getColumns();
        int height = size.getRows();
        screen.clear();
        TextGraphics tg = screen.newTextGraphics();
        for (int i = 0; i < lines.size(); i++) {
            int y = yOffset + i;
            if (y < 0 || y >= height) continue;
            String line = lines.get(i);
            // Centrado horizontal (opcional, pero queda mejor)
            int x = Math.max(0, (width - line.length()) / 2);
            if (x >= width) continue;
            // Recorte simple si se sale por la derecha
            String visible = (line.length() > width) ? line.substring(0, width) :
                    line;
            tg.putString(x, y, visible);
        }
        screen.refresh();
    }
}