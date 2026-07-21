import { useState } from 'react'

const API = '/api/shorten'

export default function App() {
  const [url, setUrl] = useState('')
  const [result, setResult] = useState(null)
  const [error, setError] = useState(null)
  const [loading, setLoading] = useState(false)
  const [copied, setCopied] = useState(false)

  async function handleSubmit(e) {
    e.preventDefault()
    setError(null)
    setResult(null)
    setCopied(false)
    setLoading(true)
    try {
      const res = await fetch(API, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ url }),
      })
      if (!res.ok) throw new Error('Failed to shorten URL')
      const data = await res.json()
      setResult(data.shorturl)
    } catch (err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  function copy() {
    navigator.clipboard.writeText(result)
    setCopied(true)
    setTimeout(() => setCopied(false), 2000)
  }

  return (
    <main className="min-h-screen bg-gray-950 flex items-center justify-center px-4">
      <div className="w-full max-w-lg">
        <h1 className="text-3xl font-bold text-white text-center mb-2">URL Shortener</h1>
        <p className="text-gray-400 text-center mb-8">Paste a long URL and get a short one instantly</p>

        <form onSubmit={handleSubmit} className="flex gap-2">
          <input
            type="url"
            required
            placeholder="https://example.com/very/long/url"
            value={url}
            onChange={e => setUrl(e.target.value)}
            className="flex-1 bg-gray-800 text-white placeholder-gray-500 border border-gray-700 rounded-lg px-4 py-3 focus:outline-none focus:border-blue-500"
          />
          <button
            type="submit"
            disabled={loading}
            className="bg-blue-600 hover:bg-blue-500 disabled:opacity-50 text-white font-semibold px-5 py-3 rounded-lg transition-colors cursor-pointer"
          >
            {loading ? 'Shortening…' : 'Shorten'}
          </button>
        </form>

        {error && (
          <p className="mt-4 text-red-400 text-sm text-center">{error}</p>
        )}

        {result && (
          <div className="mt-6 bg-gray-800 border border-gray-700 rounded-lg px-4 py-3 flex items-center justify-between gap-3">
            <a
              href={result}
              target="_blank"
              rel="noopener noreferrer"
              className="text-blue-400 hover:underline truncate"
            >
              {result}
            </a>
            <button
              onClick={copy}
              className="shrink-0 text-sm text-gray-300 hover:text-white border border-gray-600 hover:border-gray-400 px-3 py-1 rounded transition-colors cursor-pointer"
            >
              {copied ? 'Copied!' : 'Copy'}
            </button>
          </div>
        )}
      </div>
    </main>
  )
}
