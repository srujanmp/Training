const http = require('http');
const fs = require('fs');
const path = require('path');
const os = require('os');
const { execSync } = require('child_process');

const PORT = 3131;
const HOST = '0.0.0.0';
const DATA_FILE = path.join(__dirname, 'chat-data.json');

function getLanIps() {
	const interfaces = os.networkInterfaces();
	const ips = [];

	for (const entries of Object.values(interfaces)) {
		for (const entry of entries || []) {
			if (entry && entry.family === 'IPv4' && !entry.internal) {
				ips.push(entry.address);
			}
		}
	}

	return [...new Set(ips)];
}

function sendJson(response, statusCode, payload) {
	response.writeHead(statusCode, {
		'Content-Type': 'application/json; charset=utf-8',
		'Cache-Control': 'no-store',
	});
	response.end(JSON.stringify(payload));
}

function escapeHtml(text) {
	return String(text)
		.replaceAll('&', '&amp;')
		.replaceAll('<', '&lt;')
		.replaceAll('>', '&gt;')
		.replaceAll('"', '&quot;')
		.replaceAll("'", '&#39;');
}

function loadState() {
	try {
		const raw = fs.readFileSync(DATA_FILE, 'utf8');
		const parsed = JSON.parse(raw);

		if (Array.isArray(parsed)) {
			return { messages: parsed, drawing: null };
		}

		return {
			messages: Array.isArray(parsed.messages) ? parsed.messages : [],
			drawing: parsed.drawing || null,
		};
	} catch {
		return { messages: [], drawing: null };
	}
}

let { messages, drawing } = loadState();

function saveState() {
	fs.writeFileSync(DATA_FILE, JSON.stringify({ messages, drawing }, null, 2));
}

function renderPage() {
	const ips = getLanIps();
	const hostnameIps = (() => {
		try {
			return execSync('hostname -I', { encoding: 'utf8' }).trim();
		} catch {
			return ips.join(' ');
		}
	})();
	const initialDrawingDataUrl = drawing ? drawing.dataUrl : '';
	const renderedMessages = messages
		.slice(-100)
		.map((message) => {
			return `<div class="msg"><div class="body">${escapeHtml(message.text)}</div></div>`;
		})
		.join('');

	return `<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>Local Chat</title>
	<style>
		:root {
			color-scheme: dark;
			--bg: #0a0a0a;
			--panel: #111111;
			--panel-2: #151515;
			--text: #f2f2f2;
			--muted: #b0b0b0;
			--accent: #e5e5e5;
			--line: #2a2a2a;
		}
		* { box-sizing: border-box; }
		body {
			margin: 0;
			min-height: 100vh;
			font-family: "Liberation Sans", "DejaVu Sans", Arial, sans-serif;
			color: var(--text);
			background: var(--bg);
			display: grid;
			place-items: center;
			padding: 16px;
		}
		.wrap {
			width: min(1240px, 100%);
			height: min(92vh, 920px);
			display: grid;
			grid-template-columns: minmax(0, 1.2fr) minmax(320px, 0.8fr);
			gap: 12px;
		}
		.chat, .draw {
			border: 1px solid var(--line);
			background: var(--panel);
			border-radius: 8px;
			overflow: hidden;
		}
		.chat {
			display: grid;
			grid-template-rows: 1fr auto;
			height: 100%;
			background: var(--panel);
		}
		.messages {
			padding: 18px 20px;
			overflow: auto;
			display: flex;
			flex-direction: column;
			justify-content: flex-end;
			gap: 4px;
		}
		.msg {
			padding: 0;
			margin: 0;
			border: 0;
			background: transparent;
		}
		.body {
			white-space: pre-wrap;
			line-height: 1.4;
			font-size: 15px;
			color: var(--text);
			padding: 2px 0;
		}
		.composer {
			border-top: 1px solid var(--line);
			padding: 12px;
			display: grid;
			gap: 8px;
			background: var(--panel);
		}
		.row {
			display: grid;
			grid-template-columns: 1fr auto;
			gap: 8px;
		}
		.draw {
			display: grid;
			grid-template-rows: auto auto 1fr auto;
			min-height: 0;
		}
		.drawHead {
			padding: 12px 12px 8px;
			border-bottom: 1px solid var(--line);
		}
		.drawTitle {
			margin: 0;
			font-size: 16px;
			font-weight: 700;
		}
		.drawSub {
			margin: 4px 0 0;
			color: var(--muted);
			font-size: 12px;
		}
		.tools {
			padding: 10px 12px;
			display: flex;
			flex-wrap: wrap;
			gap: 8px;
			border-bottom: 1px solid var(--line);
		}
		.toolBtn {
			min-height: 36px;
			padding: 0 10px;
			border: 1px solid var(--line);
			border-radius: 999px;
			background: var(--panel-2);
			color: var(--text);
			cursor: pointer;
		}
		.toolBtn.active {
			outline: 1px solid var(--text);
		}
		.colorDot {
			width: 14px;
			height: 14px;
			border-radius: 50%;
			display: inline-block;
			vertical-align: middle;
			margin-right: 6px;
			border: 1px solid rgba(255, 255, 255, 0.18);
		}
		.boardWrap {
			position: relative;
			min-height: 0;
			background: #0d0d0d;
		}
		.board {
			display: block;
			width: 100%;
			height: 100%;
			background: #0d0d0d;
			cursor: crosshair;
			touch-action: none;
		}
		.boardStatus {
			padding: 8px 12px 12px;
			border-top: 1px solid var(--line);
			font-size: 12px;
			color: var(--muted);
		}
		input, textarea, button {
			font: inherit;
			border-radius: 6px;
			border: 1px solid var(--line);
			background: var(--panel-2);
			color: var(--text);
			outline: none;
		}
		input, textarea {
			padding: 10px 12px;
			width: 100%;
		}
		textarea {
			resize: none;
			min-height: 42px;
			max-height: 120px;
		}
		button {
			padding: 0 14px;
			font-weight: 600;
			background: var(--panel-2);
			color: var(--text);
			cursor: pointer;
			min-height: 42px;
		}
		.hint {
			color: var(--muted);
			font-size: 12px;
		}
		@media (max-width: 720px) {
			.wrap { grid-template-columns: 1fr; height: auto; }
			.row { grid-template-columns: 1fr; }
			button { width: 100%; }
			body { padding: 8px; }
			.draw { min-height: 520px; }
		}
	</style>
</head>
<body>
	<div class="wrap">
		<section class="chat">
			<div class="messages" id="messages">${renderedMessages || '<div class="hint">No messages yet. Start the chat.</div>'}</div>
			<form class="composer" id="form">
				<div class="row">
					<textarea id="text" maxlength="1000" placeholder="Write a message"></textarea>
					<button type="submit">Send</button>
				</div>
			</form>
		</section>
		<section class="draw">
			<div class="drawHead">
				<p class="drawTitle">Draw board</p>
				<p class="drawSub">One shared image for everyone. Latest save wins.</p>
			</div>
			<div class="tools" id="tools">
				<button type="button" class="toolBtn colorBtn active" data-color="#f2f2f2"><span class="colorDot" style="background:#f2f2f2"></span>White</button>
				<button type="button" class="toolBtn colorBtn" data-color="#ef4444"><span class="colorDot" style="background:#ef4444"></span>Red</button>
				<button type="button" class="toolBtn colorBtn" data-color="#22c55e"><span class="colorDot" style="background:#22c55e"></span>Green</button>
				<button type="button" class="toolBtn colorBtn" data-color="#3b82f6"><span class="colorDot" style="background:#3b82f6"></span>Blue</button>
				<button type="button" class="toolBtn colorBtn" data-color="#f59e0b"><span class="colorDot" style="background:#f59e0b"></span>Yellow</button>
				<button type="button" class="toolBtn" id="eraserBtn">Eraser</button>
				<button type="button" class="toolBtn" id="clearBtn">Clear all</button>
			</div>
			<div class="boardWrap">
				<canvas class="board" id="board"></canvas>
			</div>
			<div class="boardStatus" id="boardStatus">Loading shared board...</div>
		</section>
	</div>

	<script>
		const messagesEl = document.getElementById('messages');
		const form = document.getElementById('form');
		const textInput = document.getElementById('text');
		const board = document.getElementById('board');
		const boardWrap = board.parentElement;
		const boardStatus = document.getElementById('boardStatus');
		const colorButtons = Array.from(document.querySelectorAll('.colorBtn'));
		const eraserBtn = document.getElementById('eraserBtn');
		const clearBtn = document.getElementById('clearBtn');
		const initialDrawingDataUrl = ${JSON.stringify(initialDrawingDataUrl)};

		const boardContext = board.getContext('2d');
		let drawingActive = false;
		let isErasing = false;
		let selectedColor = '#f2f2f2';
		let remoteDrawingVersion = ${JSON.stringify(drawing ? drawing.updatedAt : '')};
		let localStrokePending = false;
		let saveTimer = null;

		function resizeBoard(preserve = true) {
			const previousImage = preserve && board.width && board.height ? board.toDataURL('image/png') : null;
			const rect = boardWrap.getBoundingClientRect();
			const ratio = window.devicePixelRatio || 1;
			board.width = Math.max(1, Math.floor(rect.width * ratio));
			board.height = Math.max(1, Math.floor(rect.height * ratio));
			boardContext.setTransform(1, 0, 0, 1, 0, 0);
			boardContext.scale(ratio, ratio);
			boardContext.fillStyle = '#0d0d0d';
			boardContext.fillRect(0, 0, rect.width, rect.height);
			if (previousImage) {
				const image = new Image();
				image.onload = () => {
					boardContext.drawImage(image, 0, 0, rect.width, rect.height);
				};
				image.src = previousImage;
			}
		}

		function positionFromEvent(event) {
			const rect = board.getBoundingClientRect();
			return {
				x: event.clientX - rect.left,
				y: event.clientY - rect.top,
			};
		}

		function drawPoint(x, y, fromX, fromY) {
			boardContext.lineCap = 'round';
			boardContext.lineJoin = 'round';
			boardContext.lineWidth = isErasing ? 24 : 4;
			boardContext.strokeStyle = selectedColor;
			boardContext.globalCompositeOperation = isErasing ? 'destination-out' : 'source-over';
			boardContext.beginPath();
			boardContext.moveTo(fromX, fromY);
			boardContext.lineTo(x, y);
			boardContext.stroke();
		}

		function repaintBoardBackground() {
			const rect = board.getBoundingClientRect();
			boardContext.globalCompositeOperation = 'source-over';
			boardContext.fillStyle = '#0d0d0d';
			boardContext.fillRect(0, 0, rect.width, rect.height);
		}

		function clearBoard(localOnly = false) {
			repaintBoardBackground();
			if (!localOnly) {
				boardStatus.textContent = 'Board cleared.';
			}
		}

		function canvasToDataUrl() {
			const exportCanvas = document.createElement('canvas');
			exportCanvas.width = board.width;
			exportCanvas.height = board.height;
			const exportContext = exportCanvas.getContext('2d');
			exportContext.fillStyle = '#0d0d0d';
			exportContext.fillRect(0, 0, exportCanvas.width, exportCanvas.height);
			exportContext.drawImage(board, 0, 0);
			return new Promise((resolve) => {
				exportCanvas.toBlob((blob) => {
					if (!blob) {
						resolve(exportCanvas.toDataURL('image/png'));
						return;
					}
					const reader = new FileReader();
					reader.onloadend = () => resolve(reader.result);
					reader.readAsDataURL(blob);
				}, 'image/webp', 0.72);
			});
		}

		async function saveBoard() {
			const dataUrl = await canvasToDataUrl();
			const response = await fetch('/api/drawing', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ dataUrl }),
			});
			const data = await response.json();
			remoteDrawingVersion = data.updatedAt || remoteDrawingVersion;
			boardStatus.textContent = 'Board saved and shared.';
		}

		function scheduleSave() {
			clearTimeout(saveTimer);
			saveTimer = setTimeout(() => {
				if (localStrokePending) {
					localStrokePending = false;
					saveBoard().catch(() => {
						boardStatus.textContent = 'Save failed.';
					});
				}
			}, 260);
		}

		async function loadDrawing() {
			const response = await fetch('/api/drawing', { cache: 'no-store' });
			const data = await response.json();

			if (!data || !data.dataUrl) {
				if (!drawingActive) {
					clearBoard(true);
				}
				boardStatus.textContent = 'No shared drawing yet.';
				remoteDrawingVersion = '';
				return;
			}

			if (data.updatedAt && data.updatedAt === remoteDrawingVersion) {
				return;
			}

			if (drawingActive) {
				return;
			}

			const image = new Image();
			image.onload = () => {
				clearBoard(true);
				const rect = board.getBoundingClientRect();
				boardContext.drawImage(image, 0, 0, rect.width, rect.height);
				remoteDrawingVersion = data.updatedAt || '';
				boardStatus.textContent = 'Shared board updated.';
			};
			image.src = data.dataUrl;
		}

		board.addEventListener('pointerdown', (event) => {
			drawingActive = true;
			board.setPointerCapture(event.pointerId);
			const { x, y } = positionFromEvent(event);
			drawPoint(x, y, x, y);
			localStrokePending = true;
			boardStatus.textContent = isErasing ? 'Erasing...' : 'Drawing...';
		});

		board.addEventListener('pointermove', (event) => {
			if (!drawingActive) return;
			const { x, y } = positionFromEvent(event);
			const lastX = board.__lastX ?? x;
			const lastY = board.__lastY ?? y;
			drawPoint(x, y, lastX, lastY);
			board.__lastX = x;
			board.__lastY = y;
			localStrokePending = true;
			scheduleSave();
		});

		function finishStroke() {
			if (!drawingActive) return;
			drawingActive = false;
			delete board.__lastX;
			delete board.__lastY;
			scheduleSave();
		}

		board.addEventListener('pointerup', finishStroke);
		board.addEventListener('pointercancel', finishStroke);
		board.addEventListener('pointerleave', finishStroke);

		colorButtons.forEach((button) => {
			button.addEventListener('click', () => {
				selectedColor = button.dataset.color;
				isErasing = false;
				colorButtons.forEach((item) => item.classList.toggle('active', item === button));
				eraserBtn.classList.remove('active');
				boardStatus.textContent = 'Pen selected.';
			});
		});

		eraserBtn.addEventListener('click', () => {
			isErasing = true;
			colorButtons.forEach((item) => item.classList.remove('active'));
			eraserBtn.classList.add('active');
			boardStatus.textContent = 'Eraser selected.';
		});

		clearBtn.addEventListener('click', async () => {
			clearBoard();
			remoteDrawingVersion = '';
			boardStatus.textContent = 'Board cleared and shared.';
			await fetch('/api/drawing', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ clear: true }),
			});
		});

		function renderMessage(message) {
			const wrapper = document.createElement('div');
			wrapper.className = 'msg';
			wrapper.innerHTML = '<div class="body"></div>';
			wrapper.querySelector('.body').textContent = message.text;
			return wrapper;
		}

		function scrollToBottom() {
			messagesEl.scrollTop = messagesEl.scrollHeight;
		}

		async function loadMessages() {
			const response = await fetch('/api/messages', { cache: 'no-store' });
			const data = await response.json();
			messagesEl.innerHTML = '';
			if (!data.length) {
				const empty = document.createElement('div');
				empty.className = 'hint';
				empty.textContent = 'No messages yet. Start the chat.';
				messagesEl.appendChild(empty);
			} else {
				data.forEach((message) => messagesEl.appendChild(renderMessage(message)));
			}
			scrollToBottom();
		}

		async function syncRemoteState() {
			await Promise.all([loadMessages(), loadDrawing()]);
		}

		form.addEventListener('submit', async (event) => {
			event.preventDefault();
			const text = textInput.value.trim();
			if (!text) return;

			await fetch('/api/messages', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ text }),
			});

			textInput.value = '';
			await loadMessages();
			textInput.focus();
		});

		textInput.addEventListener('keydown', (event) => {
			if (event.key === 'Enter' && !event.shiftKey) {
				event.preventDefault();
				form.requestSubmit();
			}
		});

		if (initialDrawingDataUrl) {
			const seedImage = new Image();
			seedImage.onload = () => {
				clearBoard(true);
				const rect = board.getBoundingClientRect();
				boardContext.drawImage(seedImage, 0, 0, rect.width, rect.height);
				boardStatus.textContent = 'Shared board loaded.';
			};
			seedImage.src = initialDrawingDataUrl;
		} else {
			clearBoard(true);
		}

		resizeBoard(false);
		window.addEventListener('resize', () => resizeBoard(true));
		syncRemoteState();
		setInterval(syncRemoteState, 2000);
	</script>
</body>
</html>`;
}

const server = http.createServer((request, response) => {
	const requestUrl = new URL(request.url, `http://${request.headers.host}`);

	if (request.method === 'GET' && requestUrl.pathname === '/') {
		response.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
		response.end(renderPage());
		return;
	}

	if (request.method === 'GET' && requestUrl.pathname === '/api/messages') {
		sendJson(response, 200, messages.slice(-100));
		return;
	}

	if (request.method === 'GET' && requestUrl.pathname === '/api/drawing') {
		sendJson(response, 200, drawing || null);
		return;
	}

	if (request.method === 'POST' && requestUrl.pathname === '/api/messages') {
		let body = '';

		request.on('data', (chunk) => {
			body += chunk;
			if (body.length > 1_000_000) {
				request.destroy();
			}
		});

		request.on('end', () => {
			try {
				const parsed = JSON.parse(body || '{}');
				const text = String(parsed.text || '').trim().slice(0, 1000);

				if (!text) {
					sendJson(response, 400, { error: 'Message text is required.' });
					return;
				}

				const message = {
					id: Date.now().toString(36) + Math.random().toString(36).slice(2, 8),
					text,
					time: new Date().toISOString(),
				};

				messages = [...messages, message].slice(-1000);
				saveState();
				sendJson(response, 201, message);
			} catch {
				sendJson(response, 400, { error: 'Invalid JSON.' });
			}
		});

		return;
	}

	if (request.method === 'POST' && requestUrl.pathname === '/api/drawing') {
		let body = '';

		request.on('data', (chunk) => {
			body += chunk;
			if (body.length > 10_000_000) {
				request.destroy();
			}
		});

		request.on('end', () => {
			try {
				const parsed = JSON.parse(body || '{}');
				if (parsed.clear) {
					drawing = null;
					saveState();
					sendJson(response, 200, { drawing: null, updatedAt: null });
					return;
				}

				const dataUrl = String(parsed.dataUrl || '').trim();
				if (!dataUrl.startsWith('data:image/')) {
					sendJson(response, 400, { error: 'Invalid drawing data.' });
					return;
				}

				drawing = {
					dataUrl,
					updatedAt: new Date().toISOString(),
				};
				saveState();
				sendJson(response, 201, drawing);
			} catch {
				sendJson(response, 400, { error: 'Invalid JSON.' });
			}
		});

		return;
	}

	sendJson(response, 404, { error: 'Not found.' });
});

server.listen(PORT, HOST, () => {
	const ips = getLanIps();
	const hostnameIps = (() => {
		try {
			return execSync('hostname -I', { encoding: 'utf8' }).trim();
		} catch {
			return ips.join(' ');
		}
	})();

	console.log(`Chat server running on http://localhost:${PORT}`);
	console.log(`Bind address: ${HOST}:${PORT}`);
	console.log(`hostname -I: ${hostnameIps || '127.0.0.1'}`);
	console.log(`LAN URLs: ${ips.length ? ips.map((ip) => `http://${ip}:${PORT}`).join('  ') : 'No LAN address found.'}`);
	console.log(`Data file: ${DATA_FILE}`);
	console.log(`If a firewall blocks access, allow TCP port ${PORT} on this machine.`);
});
