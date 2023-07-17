'use client';
import { useEffect, useState } from "react";

export default function Home() {
  const [webhookTableData, setWebhookTableData] = useState([]);

  async function getData() {
    const res = 'http://localhost:8080/api/webhook';

    try {
      const response = await fetch(res);
      const data = await response.json();
      setWebhookTableData(data);
    } catch (error) {
      console.error(error);
    }
  }

  useEffect(() => {
    getData();
  }, []);

  const headers = Object.keys(webhookTableData[0] || {});
  const rows = webhookTableData.map((data) => Object.values(data));

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <h1
        className="mb-4 text-4xl font-extrabold leading-none tracking-tight text-gray-900 md:text-5xl lg:text-6xl dark:text-white">Gitlab Webhook</h1>
      <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
        <thead>
        <tr>
          {headers.map((header, index) => (
            <th className='border border-slate-400 p-4 text-center' key={index}>{header}</th>
          ))}
        </tr>
        </thead>
        <tbody>
        {rows.map((row, rowIndex) => (
          <tr key={rowIndex}>
            {row.map((cell, cellIndex) => (
              <td className='max-w-xxxs break-words border p-4 border-slate-400' key={cellIndex}>
                <div className="relative overflow-hidden truncate hover:overflow-visible">
                  {cell}
                  <div className="absolute inset-0 opacity-50 pointer-events-none"></div>
                </div>
              </td>
            ))}
          </tr>
        ))}
        </tbody>
      </table>

      <div className="mb-32 grid text-center lg:mb-0 lg:grid-cols-4 lg:text-left">
      </div>
    </main>
  )
}
